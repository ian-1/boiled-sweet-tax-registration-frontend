/*
 * Copyright 2024 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers

import org.jsoup.Jsoup
import play.api.http.Status
import utils.IntegrationSpecHelper

class RegisterNotYetRequiredControllerISpec extends IntegrationSpecHelper {

  "GET /register-not-yet-required" should {
    val result = get("/register-not-yet-required")

    "return 200" in {
      result.status shouldBe Status.OK
    }

    "return the correct view" in {
      val document = Jsoup.parse(result.body)

      result.contentType shouldBe "text/html; charset=UTF-8"

      val panelH1 = "You do not need to register for Boiled Sweet Tax until the 1st January 2025"

      document.title shouldBe "Register Not Yet Required - Boiled Sweet Tax Registration - GOV.UK"
      document.select(".govuk-panel").select("h1").text() shouldBe panelH1
      document.select(".govuk-panel").text() shouldBe panelH1 + " You can register now voluntarily if you would like to"
      document.select("a").select(".govuk-body").text() shouldBe "Click here to register now voluntarily"
      document.select("a").select(".govuk-body").attr("href") shouldBe "http://localhost:8765/boiled-sweet-tax-registration/register-voluntary"
    }
  }

}
