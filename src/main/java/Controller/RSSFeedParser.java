package Controller;

import models.RSSFeedModel;
import models.RSSItemModel;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class RSSFeedParser
{
    static final String TITLE = "title";
    static final String DESCRIPTION = "description";
    static final String CHANNEL = "channel";
    static final String LANGUAGE = "language";
    static final String COPYRIGHT = "copyright";
    static final String LINK = "link";
    static final String AUTHOR = "author";
    static final String ITEM = "item";
    static final String PUB_DATE = "pubDate";
    static final String GUID = "guid";

    private String feedUrl;


    public RSSFeedParser(String feedUrl)
    {
        this.feedUrl = feedUrl;
    }

    public RSSFeedModel readFeed()
    {
        RSSFeedModel feed = null;

        try {
            Document document = Jsoup.connect(feedUrl).get();

            for (Element element: document.select("item"))
                System.out.println("=====> " + element);
        } catch (IOException e) {
            e.printStackTrace();
        }




//        try
//        {
//            boolean isFeedHeader = true;
//
//            // Set header values intial to the empty string
//            String description = "";
//            String title = "";
//            String link = "";
//            String language = "";
//            String copyright = "";
//            String author = "";
//            String pubdate = "";
//            String guid = "";
//
//            // First create a new XMLInputFactory
//            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
//
//            // Setup a new eventReader
//            InputStream in = url.openStream();
//            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
//
//            // read the XML document
//            while (eventReader.hasNext())
//            {
//                XMLEvent event = eventReader.nextEvent();
//                if (event.isStartElement())
//                {
//                    String localPart = event.asStartElement().getName().getLocalPart();
//                    switch (localPart)
//                    {
//                        case ITEM:
//                            if (isFeedHeader) {
//                                isFeedHeader = false;
//                                feed = new RSSFeedModel(title, link, description, language,
//                                        copyright, pubdate);
//                            }
//                            event = eventReader.nextEvent();
//                            break;
//                        case TITLE:
//                            title = getCharacterData(event, eventReader);
//                            break;
//                        case DESCRIPTION:
//                            description = getCharacterData(event, eventReader);
//                            break;
//                        case LINK:
//                            link = getCharacterData(event, eventReader);
//                            break;
//                        case GUID:
//                            guid = getCharacterData(event, eventReader);
//                            break;
//                        case LANGUAGE:
//                            language = getCharacterData(event, eventReader);
//                            break;
//                        case AUTHOR:
//                            author = getCharacterData(event, eventReader);
//                            break;
//                        case PUB_DATE:
//                            pubdate = getCharacterData(event, eventReader);
//                            break;
//                        case COPYRIGHT:
//                            copyright = getCharacterData(event, eventReader);
//                            break;
//                    }
//                } else if (event.isEndElement()) {
//                    if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
//                        RSSItemModel message = new RSSItemModel(title,description,link,author,guid);
//                        feed.getMessages().add(message);
//                        event = eventReader.nextEvent();
//                        continue;
//                    }
//                }
//            }
//        } catch (XMLStreamException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return feed;
    }

    private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

}
