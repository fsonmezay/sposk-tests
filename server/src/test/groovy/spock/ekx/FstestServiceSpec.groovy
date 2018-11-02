package spock.ekx

import grails.testing.mixin.integration.Integration
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

@Integration
class FstestServiceSpec extends Specification implements ServiceUnitTest<FstestService> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect: "fix me"
        true == true
    }

    def "name is empty"() {
        when:
        service.saveStudent("", "")

        then:
        thrown(RuntimeException)
    }

    def "name and email tests"() {

        when:
        service.saveStudent(name, email)

        then:
        def e = thrown(exception)
        e.message == errorMessage

        where:
        name | email | exception        | errorMessage
        ""   | ""    | RuntimeException | "name blank"
        "aa" | ""    | RuntimeException | "email blank"
    }
}
