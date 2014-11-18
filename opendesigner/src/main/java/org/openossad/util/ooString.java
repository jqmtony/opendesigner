package org.openossad.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class ooString {

	public static StringBuffer replace(StringBuffer buf, int start, int end, String text) {
	    int len = text.length();
	    char[] ch = new char[buf.length() + len - (end - start)];
	    buf.getChars(0, start, ch, 0);
	    text.getChars(0, len, ch, start);
	    buf.getChars(end, buf.length(), ch, start + len);
	    buf.setLength(0);
	    buf.append(ch);
	    return buf;
	  }

	  public static Integer f(String base,String searchFor){
		  int len = searchFor.length();
		  int result = 0;
		  if (len > 0) {
			  int start = base.indexOf(searchFor);
			  while (start != -1) {
		            result++;
		            start = base.indexOf(searchFor, start+len);
		      }
		  }
		  return result;
	  }
	  
	/**
	 * @param string
	 * @param string2
	 */
	public static String p(String str, String sep) {
		// TODO Auto-generated method stub
		return p(str,sep,1);
	}

	/**
	 * @param str
	 * @param sep
	 * @param i
	 * @return
	 */
	public static String p(String str, String sep, Integer i) {
		
		
		
		if (str==null ) return "";
		if (sep==null || i==0) return str;
		if (i==null) i=1;
		if (str.equals(sep)) return str;
		if (str.indexOf(sep)==-1) return str;
		
		//if (str.indexOf(sep)==-1) return "";
		if (i>f(str,sep)+1) return "";
		
		if (i==1) return str.substring(0,str.indexOf(sep, 1));
		
		Integer[] c2=new Integer[f(str,sep)+2];
		Integer pis=1;c2[0]=0;
		for (int x=0;x<str.length();x++) {
			if ( str.substring(x, x+1).equals(sep) ) {
				c2[pis]=x;
				pis++;
			}
		}
		c2[pis]=str.length();
		//if (i>f(str,sep)) { i=c2.length;c2[i]=str.length();}
		if (i>f(str,sep)+1) i=c2.length-1;
		return str.substring(c2[i-1]+1,c2[i]);	
	}
	
	/**
	 * @param str
	 * @param sep
	 * @param i
	 * @return
	 */
	public static String p(String str, String sep, Integer i,Integer i2) {
		if (str==null ) return "";
		if (sep==null || i==0) return str;
		if (i==null) i=1;
		if (str.indexOf(sep)==-1) return str;
		if (i>f(str,sep)+2) return "";
		Integer[] c2=new Integer[f(str,sep)+2];
		Integer pis=1;c2[0]=0;
		for (int x=0;x<str.length();x++) {
			if ( str.substring(x, x+1).equals(sep) ) {
				c2[pis]=x;
				pis++;
			}
		}
		c2[pis]=str.length();
		if (i2>f(str,sep)) { i2=c2.length-1;c2[i2]=str.length();}
		return str.substring(c2[i-1]+1,c2[i2]);	
	}
	/**
	 * @param value
	 * @return
	 */
	public static String[] parseObject(Object[] value) {
		Integer R=0;
		String[] str1 = new String[value.length];
		for (Object str : (Object[]) value) {
			str1[R] = (str==null) ? "" : str.toString();
			R++;
		}
		return str1;
	}
	
	
	  public static void main(String args[]) {
		    String s = "Now is the ^time^ for all ^good men^ " + "to come^ to the aid of their country.";

		    System.out.println(s);
		    System.out.println("indexOf(t) = " + s.indexOf('t'));
		    System.out.println("lastIndexOf(t) = " + s.lastIndexOf('t'));
		    System.out.println("indexOf(the) = " + s.indexOf("the"));
		    System.out.println("lastIndexOf(the) = " + s.lastIndexOf("the"));
		    System.out.println("indexOf(t, 10) = " + s.indexOf('t', 10));
		    System.out.println("lastIndexOf(t, 60) = " + s.lastIndexOf('t', 60));
		    System.out.println("indexOf(the, 10) = " + s.indexOf("the", 10));
		    System.out.println("lastIndexOf(the, 60) = " + s.lastIndexOf("the", 60));
		    
		    System.out.println(p(s,"^",2));
		    System.out.println(p(s,"^",1));
		    System.out.println(p(s,"^",4));
		    System.out.println(p(s,"^",2,10));
		    
		    s = "weke^wekande";
		    System.out.println(p(s,"^",1));
		    System.out.println(p(s,"^",2));
		  }

	
	  public static String dateformat() {
			String DATE_FORMAT = "yyyy-MM-dd H:m:s";
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			Calendar c1 = Calendar.getInstance(); // today
			return sdf.format(c1.getTime());

		}
}
