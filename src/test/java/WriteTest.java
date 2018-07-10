import Controller.RSSFeedWriter;
import models.RSSFeedModel;
import models.RSSItemModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class WriteTest {
    public static void main(String[] args) {
        // create the rss feed
        String copyright = "Copyright hold by Lars Vogel";
        String title = "Eclipse and Java Information";
        String description = "Eclipse and Java Information";
        String language = "en";
        String link = "http://www.varzesh3.com/rss/all";

        Calendar cal = new GregorianCalendar();
        Date creationDate = cal.getTime();
        SimpleDateFormat date_format = new SimpleDateFormat("EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z", Locale.US);
        String pubdate = date_format.format(creationDate);
        RSSFeedModel rssFeeder = new RSSFeedModel(title, link, description, language, copyright, pubdate);

        // now add one example entry
        RSSItemModel feed = new RSSItemModel("RSSFeed", "This is a description", "myLink", "Me","this.com");
        rssFeeder.getMessages().add(feed);

        // now write the file
        RSSFeedWriter writer = new RSSFeedWriter(rssFeeder, "articles.rss");
        try {
            writer.write();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
