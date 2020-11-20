

import java.io.FileReader;
import java.io.IOException;

import aps0.ast.AstProgramme;
import aps1.parser.Parser;
import aps1.typage.PrologTerm;

public class PrologTermMain {

	public static void main(String args[]) throws IOException {
		Parser parser;
		AstProgramme prog=null;
		PrologTerm p = new PrologTerm();
		if (args.length < 1) {
			System.out.println("Please, give the name of file .aps ...");
			return;
		}
		parser = new Parser(new FileReader(args[0]));
		try {
			parser.parse();
			prog = parser.prog;

		} catch (Exception e) {
			System.out.println("Exception: "+e.getMessage());
			return;
		}
		if (prog != null)
			try {
				System.out.println(p.visit(prog, null) + ".");
				
			} catch (Exception e) {
				System.out.println("Exception: "+e.getMessage());
				return;
			}
		else
			System.out.println("Error in parsing ...");
	}

}
