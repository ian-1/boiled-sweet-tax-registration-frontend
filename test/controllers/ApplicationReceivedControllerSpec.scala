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

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.http.Status
import play.api.test.FakeRequest
import play.api.test.Helpers._
import views.html.ApplicationReceivedPage

class ApplicationReceivedControllerSpec extends AnyWordSpec with Matchers with GuiceOneAppPerSuite {

  private val page = app.injector.instanceOf[ApplicationReceivedPage]
  private val controller = new ApplicationReceivedController(stubMessagesControllerComponents(), page)

  private val fakeRequest = FakeRequest("GET", "/application-received")

  "GET /application-received" should {
    val result = controller.show()(fakeRequest)

    "return 200" in {
      status(result) shouldBe Status.OK
    }

    "return HTML" in {
      contentType(result) shouldBe Some("text/html")
      charset(result) shouldBe Some("utf-8")
    }

    "return correct page" in {
      contentAsString(result) should include("application-received")
    }
  }
}
