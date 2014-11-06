package com.bilkentieee.mobilgunler;

import java.util.ArrayList;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.location.Location;
import com.bilkentieee.mobilgunler.R;

public class XMLHandlerForSession extends DefaultHandler{

		String elementValue = null;
		Boolean elementOn = false;
		boolean in21 = false;
		boolean in22 = false;
		boolean in23 = false;
		boolean inOturum = false;
		boolean inP0 = false;
		boolean inP1 = false;
		boolean inP2 = false;
		ArrayList<Oturum> oturumList21 = new ArrayList<Oturum>();
		ArrayList<Oturum> oturumList22 = new ArrayList<Oturum>();
		ArrayList<Oturum> oturumList23 = new ArrayList<Oturum>();
		Oturum oturum = new Oturum();

		public XMLHandlerForSession() {
			elementValue = "";

		}

		@Override
		public void startElement(String uri, String localName, String qName,
				org.xml.sax.Attributes attributes) throws SAXException {
			// TODO Auto-generated method stub
			elementOn = true;

			if (localName.equalsIgnoreCase("aralik21")) {
				in21 = true;
			} else if(localName.equalsIgnoreCase("p0")) {
				inP0 = true;
			} else if(localName.equalsIgnoreCase("p1")) {
				inP1 = true;
			} else if(localName.equalsIgnoreCase("p2")) {
				inP2 = true;
			} else if(localName.equalsIgnoreCase("aralik22")) {
				in22 = true;
			} else if(localName.equalsIgnoreCase("aralik23")) {
				in23 = true;
			}

			super.startElement(uri, localName, qName, attributes);
		}

		/**
		 * This will be called when the tags of the XML end.
		 **/

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {

			elementOn = false;
			/**
			 * Sets the values after retrieving the values from the XML tags
			 * */
			if (localName.equalsIgnoreCase("aralik21")) {
				in21 = false;
			} else if(localName.equalsIgnoreCase("p0")) {
				inP0 = false;
			} else if(localName.equalsIgnoreCase("p1")) {
				inP1 = false;
			} else if(localName.equalsIgnoreCase("p2")) {
				inP2 = false;
			} else if(localName.equalsIgnoreCase("aralik22")) {
				in22 = false;
			} else if(localName.equalsIgnoreCase("aralik23")) {
				in23 = false;
			} else if(localName.equalsIgnoreCase("saat")) {
				oturum.setTime(elementValue);
			} else if(localName.equalsIgnoreCase("konu")) {
				oturum.setSubject(elementValue);
			} else if(localName.equalsIgnoreCase("konusmaci")) {
				oturum.setSpeaker(elementValue);
			} else if(localName.equalsIgnoreCase("oturum") && in21) {
				oturum.setDay("21 Aralýk");
				oturum.setParalel(1);
				oturumList21.add(oturum);
				oturum = new Oturum();
			} else if(localName.equalsIgnoreCase("oturum") && in22) {
				oturum.setDay("22 Aralýk");
				if(inP0)
					oturum.setParalel(1);
				else if(inP1)
					oturum.setParalel(2);
				else
					oturum.setParalel(3);
				oturumList22.add(oturum);
				oturum = new Oturum();
			} else if(localName.equalsIgnoreCase("oturum") && in23) {
				oturum.setDay("23 Aralýk");
				if(inP0)
					oturum.setParalel(1);
				else if(inP1)
					oturum.setParalel(2);
				else
					oturum.setParalel(3);
				oturumList23.add(oturum);
				oturum = new Oturum();
			}  
		}
	
		/**
		 * This is called to get the tags value
		 **/
		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {

			if (elementOn) {
				elementValue = new String(ch, start, length);
				elementOn = false;
			}

		}

		public ArrayList<Oturum> getOturumList21() {
			return oturumList21;
		}
		public ArrayList<Oturum> getOturumList22() {
			return oturumList22;
		}
		public ArrayList<Oturum> getOturumList23() {
			return oturumList23;
		}

}
