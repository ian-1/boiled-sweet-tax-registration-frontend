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

class ApplicationReceivedControllerISpec extends IntegrationSpecHelper {

  "GET /application-received" should {
    val result = get("/application-received")

    "return 200" in {
      result.status shouldBe Status.OK
    }

    "return the correct view" in {
      val document = Jsoup.parse(result.body)

      result.contentType shouldBe "text/html; charset=UTF-8"

      document.title shouldBe "Application Received - Boiled Sweet Tax Registration - GOV.UK"
      document.select("h1").text() shouldBe "Your application to register for Boiled Sweet Tax has been received"
      document.select("h2").not(".govuk-visually-hidden").text() shouldBe "What happens next"
      document.select("p.govuk-body").text() shouldBe "We’ll review your registration application and respond to your supplied email within 3 weeks"
    }
  }

}
