package com.example.apple;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class RSSParser extends DefaultHandler {
	
	private final static String TAG_ITEM = "item";
	private final static String[] xmltags = { "title", "link", "description", "pubDate" };
	
	private News current_item = null;
	private ArrayList<News> list = null;
	private int current_index = -1;
	private boolean isParsing = false;
	private StringBuilder builder = new StringBuilder();
	
	public RSSParser(ArrayList<News> list)
	{
		this.list = list;
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		super.characters(ch, start, length);
		
		if(isParsing && -1 != current_index && null != builder)
		{
			builder.append(ch,start,length);
		}
	}
	
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
super.startElement(uri, localName, qName, attributes);
		
		if(localName.equalsIgnoreCase(TAG_ITEM))
		{
			current_item = new News();
			current_index = -1;
			isParsing = true;
			
			list.add(current_item);
		}
		else
		{
			current_index = itemIndexFromString(localName);
			
			builder = null;
			
			if(-1 != current_index)
				builder = new StringBuilder();
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		super.endElement(uri, localName, qName);
		
		if(localName.equalsIgnoreCase(TAG_ITEM))
		{
			isParsing = false;
		}
		else if(current_index != -1)
		{
			if(isParsing)
			{
				switch(current_index)
				{
					case 0:	current_item.setTitle(builder.toString()); 		break; 
					case 1:	current_item.setLink(builder.toString());			break;
					case 2:	current_item.setDescription(builder.toString());	break;
					case 3:	current_item.setDate(builder.toString());			break;
				}
			}
		}
	}
	
	private int itemIndexFromString(String tag_name){
		int item_index = -1;

		for(int index= 0; index<xmltags.length; ++index)
		{
			if(tag_name.equalsIgnoreCase(xmltags[index]))
			{
				item_index = index;
				
				break;
			}
		}
		
		return item_index;
	}

}
