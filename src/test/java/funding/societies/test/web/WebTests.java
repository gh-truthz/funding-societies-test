package funding.societies.test.web;

import funding.societies.web.pages.FundingSocietiesPages;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class WebTests {
    @Test
    public void testBasicControls() {
        FundingSocietiesPages page = new FundingSocietiesPages();
        page
                .open()
                .openStatistic()
        ;
        Assertions.assertThat(page.isSummariedDataDisplayed()).isTrue();
        Assertions.assertThat(page.isTabsDisplayed()).isTrue();
        page
                .openGeneralTab()
                .openGeneralAmountDisbursed()
                .openGeneralDefaultRate()
                .openGeneralTotalApprove()
                .openDisbursementTab()
                .openRepaymentTab();
        page.close();
    }
}
