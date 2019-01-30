package io.foxdrift.api

import org.junit.jupiter.api.Assertions.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object LoginControllerTest: Spek({
    Feature("Login") {
       Scenario("loginRequest successfully") {
           When("I enter my username") {

           }

           Then("I should loginRequest successfully") {
                assertEquals(2,3)
           }
       }
    }
})