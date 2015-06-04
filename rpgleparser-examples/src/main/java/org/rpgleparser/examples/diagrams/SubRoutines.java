package org.rpgleparser.examples.diagrams;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.jgrapht.DirectedGraph;
import org.jgrapht.ext.ComponentAttributeProvider;
import org.jgrapht.ext.DOTExporter;
import org.jgrapht.ext.VertexNameProvider;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.rpgleparser.RpgLexer;
import org.rpgleparser.RpgParser;
import org.rpgleparser.RpgParser.BegsrContext;
import org.rpgleparser.RpgParser.CsBEGSRContext;
import org.rpgleparser.RpgParser.CsCALLContext;
import org.rpgleparser.RpgParser.CsEXSRContext;
import org.rpgleparser.RpgParser.Cspec_fixed_standardContext;
import org.rpgleparser.RpgParser.EndsrContext;
import org.rpgleparser.RpgParser.FreeBEGSRContext;
import org.rpgleparser.RpgParser.Op_exsrContext;
import org.rpgleparser.RpgParser.StatementContext;
import org.rpgleparser.RpgParserBaseVisitor;
import org.rpgleparser.utils.FixedWidthBufferedReader;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.ObjectDescription;

/**
 * Generates a DOT graph of the subroutines and program calls within a simple program.
 * Uses JGraphT library.
 * Use Graphviz to generate the image.
 * @author ryaneberly
 *
 */
public class SubRoutines {

	public static void main(String ... a) throws Exception{
		
		AS400 as400sys = new AS400("gmcc400");
		ObjectDescription d = new ObjectDescription(as400sys,"/QSYS.LIB/%LIBL%.LIB/PPR900.PGM");
		System.out.println(d.getValue(ObjectDescription.TEXT_DESCRIPTION));
		as400sys.disconnectAllServices();
		//FileInputStream fis = new FileInputStream("C:\\Users\\eberlyrh\\git\\rpgleparser\\src\\test\\resources\\scratch.rpgle");
		FileInputStream fis = new FileInputStream("C:\\temp\\CLRT016G.MBR");
		byte b[] = new byte[fis.available()];
		fis.read(b);
		String inputString = new String(b);
		final List<String> errors = new ArrayList<String>();
        final ANTLRInputStream input = new ANTLRInputStream(new FixedWidthBufferedReader(inputString));
		final RpgLexer lexer = new RpgLexer(input);
        final CommonTokenStream tokens = new CommonTokenStream(lexer);
        final RpgParser parser = new RpgParser(tokens);
		final ParseTree parseTree = parser.r();
		
		//final String actualTree = TreeUtils.printTree(parseTree, parser);
		//System.out.println(actualTree);
		
		SRListener listener = new SRListener(tokens);
		listener.visit(parseTree);
		StringWriter sw = new StringWriter();
		DOTExporter exporter = new DOTExporter(listener,null,null,listener,null);
		exporter.export(sw,listener.getDirectedGraph());
		System.out.println("-----------");
		String diagramText = "digraph G {\r\n" + 
			    		"        ordering=\"out\";\r\n" + 
			    		"	    rankdir=\"LR\";\r\n" + 
			    		"	    node [\r\n" + 
			    		"                fontname = \"arial\";\r\n" + 
			    		"        		fontcolor = \"#000011\";\r\n" + 
			    		"                fontsize = 8;\r\n" + 
			    		"                shape = \"record\";\r\n" + 
			    		"                //style = bold;\r\n" + 
			    		"        ]\r\n" + 
			    		"\r\n" + 
			    		"        edge [\r\n" + 
			    		"                fontname = \"arial\"\r\n" + 
			    		"                fontsize = 8;\r\n" + 
			    		"        ] //" + sw;

		System.out.println(diagramText);
	    FileWriter fileWriter = new FileWriter("C:\\temp\\out.dot");
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    printWriter.print(diagramText);
	    printWriter.close();
	    as400sys.disconnectAllServices();
	    //JTopen UI frame, prevents program termination.
	    System.exit(0);
	}
	
	public static class SRListener extends RpgParserBaseVisitor<String> implements VertexNameProvider<String>,ComponentAttributeProvider<String>{

		final CommonTokenStream tokens;
		Set<String> calls = new HashSet<String>();
		Map<String,String> srDescriptions = new HashMap<String,String>();
		Stack<String> stack = new Stack<String>();
		DirectedGraph<String, DefaultEdge> directedGraph =
	            new DefaultDirectedGraph<String, DefaultEdge>
	            (DefaultEdge.class);
		
		public DirectedGraph<String, DefaultEdge> getDirectedGraph() {
			return directedGraph;
		}

		public SRListener(CommonTokenStream tokens){
			this.tokens=tokens;
			stack.push("MAINLINE");
			directedGraph.addVertex("MAINLINE");
		}
		
		@Override
		public String visitCspec_fixed_standard(Cspec_fixed_standardContext ctx) {
			if(ctx.operation != null && ctx.operation.getText().toUpperCase().startsWith("CAS")){
				createVertexTo(ctx.cspec_fixed_standard_parts().resultType().getText());
			}
			return super.visitCspec_fixed_standard(ctx);
		}
		
		@Override
		public String visitCsCALL(CsCALLContext ctx) {
			String pgmname=ctx.cspec_fixed_standard_parts().factor().getText().trim().replaceAll("'","");
			calls.add(pgmname);
			List<Token> toks = tokens.getHiddenTokensToLeft(((StatementContext)ctx.parent.parent.parent).start.getTokenIndex());
			if(toks != null){
				for(Token tok: toks){
					if (!tok.getText().equals(" *") && !tok.getText().startsWith("==============") && tok.getText().trim().length()>0){
						srDescriptions.put(pgmname, tok.getText().trim());
						break;
					}
				}
			}
			toks = tokens.getHiddenTokensToRight(((StatementContext)ctx.parent.parent.parent).stop.getTokenIndex());
			if(toks != null){
				int cLines = 0;
				for(Token tok: toks){
					//After 2nd ==== line. stop looking.
					if (tok.getText().startsWith("==============") ){
						cLines++;
						if(cLines >= 2){
							break;
						}
					}else if (!tok.getText().equals(" *") && tok.getText().trim().length()>0){
						srDescriptions.put(pgmname, tok.getText().trim());
						break;
					}
				}
			}
			
			createVertexTo(pgmname);
			return super.visitCsCALL(ctx);
		}

		@Override
		public String visitBegsr(BegsrContext ctx) {
			String retval = super.visitBegsr(ctx);
			List<Token> toks = tokens.getHiddenTokensToLeft(ctx.start.getTokenIndex());
			if(toks != null){
				for(Token tok: toks){
					if (!tok.getText().equals(" *") && !tok.getText().startsWith("==============") && tok.getText().trim().length()>0){
						srDescriptions.put(stack.peek(), tok.getText().trim());
						break;
					}
				}
			}
			toks = tokens.getHiddenTokensToRight(ctx.stop.getTokenIndex());
			if(toks != null){
				int cLines = 0;
				for(Token tok: toks){
					//After 2nd ==== line. stop looking.
					if (tok.getText().startsWith("==============") ){
						cLines++;
						if(cLines >= 2){
							break;
						}
					}else if (!tok.getText().equals(" *") && tok.getText().trim().length()>0){
						srDescriptions.put(stack.peek(), tok.getText().trim());
						break;
					}
				}
			}
			return retval;
		}

		@Override
		public String visitCsBEGSR(CsBEGSRContext ctx) {
			System.out.println(ctx.factor1.getText());
			stack.push(ctx.factor1.getText());
			directedGraph.addVertex(ctx.factor1.getText());
			return super.visitCsBEGSR(ctx);
		}

		@Override
		public String visitFreeBEGSR(FreeBEGSRContext ctx) {
			System.out.println(ctx.identifier().getText());
			stack.push(ctx.identifier().getText());
			directedGraph.addVertex(ctx.identifier().getText());
			return super.visitFreeBEGSR(ctx);
		}

		@Override
		public String visitEndsr(EndsrContext ctx) {
			// TODO Auto-generated method stub
			return super.visitEndsr(ctx);
		}

		void createVertexTo(String x){
			if(!directedGraph.containsVertex(x)){
				directedGraph.addVertex(x);
			}
			directedGraph.addEdge(stack.peek(), x);
			
		}
		@Override
		public String visitCsEXSR(CsEXSRContext ctx) {
			createVertexTo(ctx.cspec_fixed_standard_parts().factor().getText());
			return super.visitCsEXSR(ctx);
		}

		@Override
		public String visitOp_exsr(Op_exsrContext ctx) {
			if(!directedGraph.containsVertex(ctx.identifier().getText())){
				directedGraph.addVertex(ctx.identifier().getText());
			}
			directedGraph.addEdge(stack.peek(), ctx.identifier().getText());
			return super.visitOp_exsr(ctx);
		}

		

		public Map<String, String> getComponentAttributes(String arg0) {
			if(srDescriptions.containsKey(arg0) || calls.contains(arg0)){
				HashMap<String, String> h = new HashMap<String,String>();
				if(srDescriptions.containsKey(arg0)){
					h.put("label", arg0 + " | " + srDescriptions.get(arg0).replaceAll("[^\\x20-\\x7E]", ""));
				}
				if(calls.contains(arg0)){
					h.put("style", "filled");
					h.put("fillcolor", "lightBlue");
				}
				return h;
			}
			return null;
		}

		public String getVertexName(String arg0) {
			return arg0.replace('#','3').replaceAll("[^a-zA-Z0-9]","x");
		}
		
		//------------------
		
		
	}
}
