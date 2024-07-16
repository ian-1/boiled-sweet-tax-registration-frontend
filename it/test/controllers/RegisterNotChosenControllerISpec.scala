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

class RegisterNotChosenControllerISpec extends IntegrationSpecHelper {

  "GET /register-not-chosen" should {
    "return 200" in {
      val result = get("/register-not-chosen")

      result.status shouldBe Status.OK
    }

    "return the correct view" in {
      val result = get("/register-not-chosen")
      val document = Jsoup.parse(result.body)

      result.contentType shouldBe "text/html; charset=UTF-8"

      document.title shouldBe "Chosen Not To Register - Boiled Sweet Tax Registration - GOV.UK"
      document.select("h1").text() shouldBe "You have chosen not to register for Boiled Sweet Tax"
      document.select("h2").not(".govuk-visually-hidden").text() shouldBe "What if my details change"
      document.select("p.govuk-body").text() shouldBe "If any of the details you provided change you can re-use this service to determine your requirement to register for Boiled Sweet Tax"
    }
  }

}
