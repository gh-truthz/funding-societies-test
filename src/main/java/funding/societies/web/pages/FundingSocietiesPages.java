package funding.societies.web.pages;

import funding.societies.web.Browser;
import org.openqa.selenium.By;

public class FundingSocietiesPages {
    private final static String url = "https://fundingsocieties.com/";
    private final static String statistic = "//*[@id=\"ssr-injection-point\"]/header/div/nav/ul/li[4]/a";
    private final static String total_funded = "/html/body/modalku-app/div[1]/progress-page/div/div[2]/div[1]/div[2]/div[1]/div";
    private final static String no_financing = "/html/body/modalku-app/div[1]/progress-page/div/div[2]/div[1]/div[2]/div[2]/div";
    private final static String default_rate = "/html/body/modalku-app/div[1]/progress-page/div/div[2]/div[1]/div[2]/div[3]/div";
    private final static String financing_fulfillment_rate = "/html/body/modalku-app/div[1]/progress-page/div/div[2]/div[1]/div[2]/div[4]/div";
    private final static String generic_tab = "/html/body/modalku-app/div[1]/progress-page/div/div[3]/div[1]/button[1]";
    private final static String generic_tab_total_approve = "/html/body/modalku-app/div[1]/progress-page/div/div[3]/div[2]/div[1]/div[2]/label[1]";
    private final static String generic_tab_amount_disbursed = "/html/body/modalku-app/div[1]/progress-page/div/div[3]/div[2]/div[1]/div[2]/label[2]";
    private final static String generic_tab_default_rate = "/html/body/modalku-app/div[1]/progress-page/div/div[3]/div[2]/div[1]/div[2]/label[3]";
    private final static String repayment_tab = "/html/body/modalku-app/div[1]/progress-page/div/div[3]/div[1]/button[2]";
    private final static String disbursement_tab = "/html/body/modalku-app/div[1]/progress-page/div/div[3]/div[1]/button[3]";
    private Browser browser;

    public FundingSocietiesPages() {
        this.browser = new Browser();
    }

    public FundingSocietiesPages open() {
        this.browser.open(url);
        return this;
    }

    public void close() {
        this.browser.close();
    }

    public FundingSocietiesPages openStatistic() {
        browser.click(statistic);
        return this;
    }

    public FundingSocietiesPages openGeneralTab() {
        browser.click(generic_tab);
        return this;
    }

    public FundingSocietiesPages openGeneralTotalApprove() {
        browser.click(generic_tab_total_approve);
        return this;
    }

    public FundingSocietiesPages openGeneralAmountDisbursed() {
        browser.click(generic_tab_amount_disbursed);
        return this;
    }

    public FundingSocietiesPages openGeneralDefaultRate() {
        browser.click(generic_tab_default_rate);
        return this;
    }

    public FundingSocietiesPages openRepaymentTab() {
        browser.click(repayment_tab);
        return this;
    }

    public FundingSocietiesPages openDisbursementTab() {
        browser.click(disbursement_tab);
        return this;
    }

    public boolean isSummariedDataDisplayed() {
        return browser.getWebDriver().findElement(By.xpath(total_funded)).isDisplayed() &&
                browser.getWebDriver().findElement(By.xpath(no_financing)).isDisplayed() &&
                browser.getWebDriver().findElement(By.xpath(default_rate)).isDisplayed() &&
                browser.getWebDriver().findElement(By.xpath(financing_fulfillment_rate)).isDisplayed()
                ;
    }

    public boolean isTabsDisplayed() {
        return browser.getWebDriver().findElement(By.xpath(generic_tab)).isDisplayed() &&
                browser.getWebDriver().findElement(By.xpath(disbursement_tab)).isDisplayed() &&
                browser.getWebDriver().findElement(By.xpath(repayment_tab)).isDisplayed()
                ;
    }

}
