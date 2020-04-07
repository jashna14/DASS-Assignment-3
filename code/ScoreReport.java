

import java.io.*;
import java.util.Vector;
import java.util.Iterator;
import java.net.*;
import java.awt.print.*;

public class ScoreReport {

	private String content;
	private void setContent(Bowler bowler,int[] scores,int games){
		content = "";
		content += "--Lucky Strike Bowling Alley Score Report--\n\n";
		content += "Report for " + bowler.getFullName() + ", aka \"" + bowler.getNickName() + "\":\n\n";
		content += "Final scores for this session: "+scores[0];
		for (int i = 1; i < games; i++){
			content += ", " + scores[i];
		}
		content += ".\n\n\nPrevious scores by date: \n";
	}

	private Iterator getAllSCores(String nick){
		Vector AllScores = null;
		try{
			AllScores = ScoreHistoryFile.getScores(nick);
		} catch (Exception e){System.err.println("Error: " + e);}

		Iterator scoreIt = AllScores.iterator();
		return scoreIt;
	}

	public ScoreReport( Bowler bowler, int[] scores, int games ) {
		setContent(bowler,scores,games);
		Iterator scoreIt = getAllSCores(bowler.getNickName());
		while (scoreIt.hasNext()){
			Score score = (Score) scoreIt.next();
			content += "  " + score.getDate() + " - " +  score.getScore() + "\n";
		}
		content += "\n\n";
		content += "Thank you for your continuing patronage.";

	}

	public void sendEmail(String recipient) {
		try {
			Socket s = new Socket("osfmail.rit.edu", 25);
			BufferedReader in =
				new BufferedReader(
					new InputStreamReader(s.getInputStream(), "8859_1"));
			BufferedWriter out =
				new BufferedWriter(
					new OutputStreamWriter(s.getOutputStream(), "8859_1"));

			String boundary = "DataSeparatorString";

			// here you are supposed to send your username
			sendln(in, out, "HELO world");
			sendln(in, out, "MAIL FROM: <mda2376@rit.edu>");
			sendln(in, out, "RCPT TO: <" + recipient + ">");
			sendln(in, out, "DATA");
			sendln(out, "Subject: Bowling Score Report ");
			sendln(out, "From: <Lucky Strikes Bowling Club>");

			sendln(out, "Content-Type: text/plain; charset=\"us-ascii\"\r\n");
			sendln(out, content + "\n\n");
			sendln(out, "\r\n");

			sendln(in, out, ".");
			sendln(in, out, "QUIT");
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendPrintout() {
		PrinterJob job = PrinterJob.getPrinterJob();

		PrintableText printobj = new PrintableText(content);

		job.setPrintable(printobj);

		if (job.printDialog()) {
			try {
				job.print();
			} catch (PrinterException e) {
				System.out.println(e);
			}
		}

	}

	public void sendln(BufferedReader in, BufferedWriter out, String s) {
		try {
			out.write(s + "\r\n");
			out.flush();
			// System.out.println(s);
			s = in.readLine();
			// System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendln(BufferedWriter out, String s) {
		try {
			out.write(s + "\r\n");
			out.flush();
			System.out.println(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
