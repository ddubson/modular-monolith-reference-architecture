package com.ddubson.monolith.controllers.user

import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object LoginControllerTest: Spek({
    Feature("Login") {
       Scenario("login successfully") {
           When("I enter my username") {

           }

           Then("I should login successfully") {
                assertEquals(2,3)
           }
       }
    }
})