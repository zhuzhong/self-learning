package ch08;

import java.io.IOException;
import java.io.Writer;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;


//将html转化为txt
public class LineBreakingTagStripper 
 extends HTMLEditorKit.ParserCallback {

  private Writer out;
  private String lineSeparator;
  
  public LineBreakingTagStripper(Writer out) {
    this(out, System.getProperty("line.separator", "\r\n")); 
  }  
  
  public LineBreakingTagStripper(Writer out, String lineSeparator) {
    this.out = out; 
    this.lineSeparator = lineSeparator;
  }  
  
  public void handleText(char[] text, int position) {
    try {
      out.write(text);
      out.flush();
    }
    catch (IOException ex) {
      System.err.println(ex); 
    }
  }
  
  public void handleEndTag(HTML.Tag tag, int position) {

    try {
      if (tag.isBlock()) {
        out.write(lineSeparator);
        out.write(lineSeparator);
      }
      else if (tag.breaksFlow()) {
        out.write(lineSeparator);
      }
    }
    catch (IOException ex) {
      System.err.println(ex); 
    }
    
  }
  public void handleSimpleTag(HTML.Tag tag, 
   MutableAttributeSet attributes, int position) {
    
    try {
      if (tag.isBlock()) {
        out.write(lineSeparator);
        out.write(lineSeparator);
      }
      else if (tag.breaksFlow()) {
        out.write(lineSeparator);
      }
      else {
        out.write(' '); 
      }
    }
    catch (IOException ex) {
      System.err.println(ex); 
    }
 
  }
  
} 
