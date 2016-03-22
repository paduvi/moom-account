package com.chamgroup.face.demo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.chamgroup.face.account.Account;
import com.chamgroup.face.account.FaceGroup;
import com.chamgroup.face.main.WebDriverConnector;
import com.chamgroup.face.user.LoginLogoutAction;
import com.chamgroup.face.demo.CheckPageLoaded;;

//import com.chamgroup.face.user.User;
public class InfoGroup {
	private static final String List = null;
	protected WebDriverConnector connector;

	public InfoGroup(WebDriverConnector connector) {
		this.connector = connector;
	}// i

	public void goGroups() throws InterruptedException {
		System.out.println("goGroups()");
		// connector.getDriver().get("https://www.facebook.com/groups/?category=membership");
		List<WebElement> nameGroups = connector.getDriver()
				.findElements(By.xpath("//div[contains(@id, 'group_browse')]"));
		for (int i = 0; i <= nameGroups.size(); i++) {
			System.out.println("goGroups() ---> " + i);
			System.out.println(nameGroup(nameGroups.get(i)));
			System.out.println(hrefGroup(nameGroups.get(i)));
			infoGroup(hrefGroup(nameGroups.get(i)));
		}
	}

	public String nameGroup(WebElement groupBrowse) {
		String name = groupBrowse
				.findElement(By
						.xpath(".//div[@class='groupsRecommendedContent']//a[contains(@data-hovercard, '/ajax/hovercard/group.php')]"))
				.getText();
		return name;
	}

	public String hrefGroup(WebElement groupBrowse) {
		String href = groupBrowse
				.findElement(By
						.xpath(".//div[@class='groupsRecommendedContent']//a[contains(@data-hovercard, '/ajax/hovercard/group.php')]"))
				.getAttribute("href");
		return href;
	}

	public int countMembersGroup() {
		if(connector.getDriver().findElements(By.xpath("//*[@id='count_text']")).size() != 0){
			String countMembers = connector.getDriver().findElement(By.xpath("//*[@id='count_text']")).getText();
			// System.out.println(countMembers);
			String temp[] = countMembers.split(" ");
			// String result = yourString.replaceAll("[-+.^:,]","");
			return Integer.parseInt(temp[0].replaceAll("[.,]", ""));
		}else
			return -1;
	}

	public String accessmodfierGroup() {
		System.out.println("accessmodfierGroup");
		String accessmodfier = connector.getDriver()
				.findElement(By.xpath(".//*[@class='groupsJumpBarContainer']/div[2]/div/div/div[2]/div")).getText();
		return accessmodfier;
	}

	public int userNums(String href) {
		/*
		 * CheckPageLoaded check = new CheckPageLoaded();
		 * connector.getDriver().findElement(By.cssSelector("body")).sendKeys(
		 * Keys.CONTROL + "t"); ArrayList<String> tabs = new
		 * ArrayList<String>(connector.getDriver().getWindowHandles());
		 * connector.getDriver().switchTo().window(tabs.get(1));
		 */
		connector.getDriver().get(href);
		// check.checkPageIsReady(connector.getDriver());
		int countUser = countMembersGroup();
		// System.out.println(accessmodfierGroup());
		// connector.getDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL
		// + "w");
		// connector.getDriver().switchTo().window(tabs.get(0));
		return countUser;
	}

	public void infoGroup(String href) {
		System.out.println("infoGroup");
		CheckPageLoaded check = new CheckPageLoaded();
		connector.getDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		ArrayList<String> tabs = new ArrayList<String>(connector.getDriver().getWindowHandles());
		connector.getDriver().switchTo().window(tabs.get(1));
		connector.getDriver().get(href);
		check.checkPageIsReady(connector.getDriver());
		System.out.println(countMembersGroup());
		// System.out.println(accessmodfierGroup());
		connector.getDriver().findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "w");
		connector.getDriver().switchTo().window(tabs.get(0));
	}

	public void loadMemberGroup(String linkGroup) {
		CheckPageLoaded check = new CheckPageLoaded();
		connector.getDriver().get(linkGroup);

		Actions actions = new Actions(connector.getDriver());
		List<WebElement> countGroups = connector.getDriver()
				.findElements(By.xpath("//div[contains(@id, 'group_browse')]"));
		System.out.println(" loadMemberGroup ==> countGroup: " + countGroups.size());
		if (check.checkPageIsReady(connector.getDriver())) {
			connector.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			actions.sendKeys(Keys.chord(Keys.CONTROL, Keys.END)).perform();
			connector.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			List<WebElement> seeMoresArea = connector.getDriver()
					.findElements(By.xpath(".//*[@id='group-browse-seemoremembership']"));
			while (check.checkPageIsReady(connector.getDriver()) && seeMoresArea.size() != 0) {
				actions.sendKeys(Keys.chord(Keys.CONTROL, Keys.END)).perform();
				connector.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
				seeMoresArea = connector.getDriver()
						.findElements(By.xpath(".//*[@id='group-browse-seemoremembership']"));
			}
		}
		System.out.println("THE END!!!!!");
		countGroups = connector.getDriver().findElements(By.xpath("//div[contains(@id, 'group_browse')]"));
		System.out.println("countGroup: " + countGroups.size());
	}

	public List<FaceGroup> infoGroup(Account user) throws Exception {
		List<FaceGroup> groups = new LinkedList<>();
		System.out.println("infoGroup()");
		// connector.getDriver().get("https://www.facebook.com/groups/?category=membership");
		loadMemberGroup("https://www.facebook.com/groups/?category=membership");
		List<WebElement> countGroups = connector.getDriver()
				.findElements(By.xpath("//div[contains(@id, 'group_browse')]"));
		for (WebElement countGroup : countGroups) {
			FaceGroup group = new FaceGroup();
			group.setLink(hrefGroup(countGroup));
			group.setTitle(nameGroup(countGroup));

			groups.add(group);
		}
		FacebookUserRestful.saveGroup(user.getUsername(), groups);
		for (FaceGroup group : groups) {
			String href = group.getLink();
//<<<<<<< HEAD
//			group.setUserNums(userNums(href));
////			FacebookUserRestful.editGroupMember(user.getUsername(), group);
//			FacebookUserRestful.saveGroup(user.getUsername(), group);
//=======
			int num = userNums(href);
			System.out.println(href);
			System.out.println(group.getTitle());
			System.out.println("Number of member: " + num);
			group.setUserNums(num);
			FacebookUserRestful.editGroupMember(user.getUsername(), group);
		}
		FacebookUserRestful.viewGroup(user.getUsername());

		return groups;
	}

	public static void main(String[] args) throws InterruptedException {
		WebDriverConnector connector = new WebDriverConnector();
		LoginLogoutAction loginAction = new LoginLogoutAction(connector.getDriver());
		Account user = new Account();
		user.setUsername("anhtuant992@gmail.com");
		user.setPassword("0975451993");
		loginAction.login(user);

		InfoGroup infoGroup = new InfoGroup(connector);

		infoGroup.loadMemberGroup("https://www.facebook.com/groups/?category=membership");
		infoGroup.goGroups();

		loginAction.logout();
	}

}
