package ch08;

import javax.swing.*;
import javax.swing.event.*;

public class LinkFollower implements HyperlinkListener {

	private JEditorPane pane;

	public LinkFollower(JEditorPane pane) {
		this.pane = pane;
	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent evt) {

		if (HyperlinkEvent.EventType.ACTIVATED == evt.getEventType()) {
			try {
				pane.setPage(evt.getURL());
			} catch (Exception ex) {
				pane.setText("<html>Could not load " + evt.getURL() + "</html>");
			}
		}else{
			System.out.println("not ==");
			System.out.println("not =="+evt.getEventType());
		}

	}

}
