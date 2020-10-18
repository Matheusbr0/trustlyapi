package com.example.trustlyapi;

import com.example.trustlyapi.githubextractor.GitHubDirectory;
import com.example.trustlyapi.githubextractor.html.HtmlUtil;
import com.example.trustlyapi.githubextractor.html.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class HtmlUtilTest {
	@Test
	void getTagWithTextInInitTagTest() throws Exception {
		Tag tag = HtmlUtil.getTagWithTextInInitTag(this.getTestHtml(), "div", GitHubDirectory.DIV_FILES_TAG_CONTENT);
		Assert.isTrue(tag != null, "The Tag object should be found given the HTML of the test!");
	}

	private String getTestHtml(){
		return new StringBuilder("<!DOCTYPE html>\n")
		.append("<html lang=\"en\">\n")
		.append("  <body class=\"logged-in env-production page-responsive\">\n")
		.append("    <div role=\"grid\" aria-labelledby=\"files\" class=\"Details-content--hidden-not-important js-navigation-container js-active-navigation-container d-md-block\" data-pjax>\n")
		.append("      <div class=\"sr-only\" role=\"row\">\n")
		.append("        <div role=\"columnheader\">Type</div>\n")
		.append("        <div role=\"columnheader\">Name</div>\n")
		.append("        <div role=\"columnheader\" class=\"d-none d-md-block\">Latest commit message</div>\n")
		.append("        <div role=\"columnheader\">Commit time</div>\n")
		.append("      </div>\n")

		.append("        <div role=\"row\" class=\"Box-row Box-row--focus-gray py-2 d-flex position-relative js-navigation-item \">\n")
		.append("          <div role=\"gridcell\" class=\"mr-3 flex-shrink-0\" style=\"width: 16px;\">\n")
		.append("              <svg color=\"gray-light\" aria-label=\"File\" height=\"16\" class=\"octicon octicon-file text-gray-light\" viewBox=\"0 0 16 16\" version=\"1.1\" width=\"16\" role=\"img\"><path fill-rule=\"evenodd\" d=\"M3.75 1.5a.25.25 0 00-.25.25v11.5c0 .138.112.25.25.25h8.5a.25.25 0 00.25-.25V6H9.75A1.75 1.75 0 018 4.25V1.5H3.75zm5.75.56v2.19c0 .138.112.25.25.25h2.19L9.5 2.06zM2 1.75C2 .784 2.784 0 3.75 0h5.086c.464 0 .909.184 1.237.513l3.414 3.414c.329.328.513.773.513 1.237v8.086A1.75 1.75 0 0112.25 15h-8.5A1.75 1.75 0 012 13.25V1.75z\"></path></svg>\n")
		
		.append("          </div>\n")
		
		.append("          <div role=\"rowheader\" class=\"flex-auto min-width-0 col-md-2 mr-3\">\n")
		.append("            <span class=\"css-truncate css-truncate-target d-block width-fit\"><a class=\"js-navigation-open link-gray-dark\" title=\"teste1.txt\" href=\"/Matheusbr0/testPublic/blob/main/teste1.txt\">teste1.txt</a></span>\n")
		.append("          </div>\n")
		
		.append("          <div role=\"gridcell\" class=\"flex-auto min-width-0 d-none d-md-block col-5 mr-3\" >\n")
		.append("              <div class=\"Skeleton Skeleton--text col-7\">&nbsp;</div>\n")
		.append("          </div>\n")
		
		.append("          <div role=\"gridcell\" class=\"text-gray-light text-right\" style=\"width:100px;\">\n")
		.append("              <div class=\"Skeleton Skeleton--text\">&nbsp;</div>\n")
		.append("          </div>\n")
		
		.append("        </div>\n")
		.append("    </div>\n")
		.append("  </body>\n")
		.append("</html>\n").toString();
	}
}
