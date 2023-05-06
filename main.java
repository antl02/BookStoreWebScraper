// A Simple Web-Scraper program that prints the top 5 books that are currently displayed on https://books.toscrape.com/ using HTMLUnit
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;

public class Main {

	public static void main(String[] args) throws Exception {
		final WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setJavaScriptEnabled(false);
		
		
		final HtmlPage page = webClient.getPage("https://books.toscrape.com/");
		
		System.out.println("--Current Top 5 Books on https://books.toscrape.com/--" + "\n");
		
		String xpath;
		HtmlAnchor bookTitle;
		HtmlParagraph bookRating;
		String bookRatingStr;
		HtmlParagraph bookPrice;
		HtmlParagraph bookAvailability;
		// Locates each book item and prints the attributes above in order
		for (int i = 1; i <= 5; i++) {
			// Base of book XPath
			xpath = "//*[@id=\"default\"]/div/div/div/div/section/div[2]/ol/li[" + i + "]";
			
			bookTitle = (HtmlAnchor) page.getByXPath(xpath + "/article/h3/a").get(0);
			System.out.println(bookTitle.getAttribute("title"));
			
			bookRating = (HtmlParagraph) page.getByXPath(xpath + "/article/p").get(0);
			bookRatingStr = bookRating.getAttribute("class");
			bookRatingStr = bookRatingStr.substring(12, bookRatingStr.length()) + "-Star Rating";
			System.out.println(bookRatingStr);
			
			bookPrice = (HtmlParagraph) page.getByXPath(xpath + "/article/div[2]/p[1]").get(0);
			System.out.println(bookPrice.asNormalizedText());
			
			bookAvailability = (HtmlParagraph) page.getByXPath(xpath + "/article/div[2]/p[2]").get(0);
			System.out.println(bookAvailability.asNormalizedText());
			
			System.out.println();
		}
		
		
		webClient.close();
		
	}

}
