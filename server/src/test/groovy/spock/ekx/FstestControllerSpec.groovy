package spock.ekx

import grails.testing.mixin.integration.Integration
import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification
import spock.lang.Unroll
import static javax.servlet.http.HttpServletResponse.SC_METHOD_NOT_ALLOWED
import static javax.servlet.http.HttpServletResponse.SC_OK

@Integration
class FstestControllerSpec extends Specification implements ControllerUnitTest<FstestController> {

//    FstestService fstestService = new FstestService()
    def setup() {
        FstestService fstestService = Mock(FstestService)
        controller.fstestService = fstestService
    }

    def cleanup() {
    }


    def "Controller.createStudent test"() {
        given:
        request.json = '{"name":"ferdi","email":"ferdi@Ekinoks"}'
        request.method = 'POST'

        when:
        controller.createStudent()

        then:
        response.status == 200
    }

    @Unroll
    def "createStudent does not accept #method requests"(String method) {
        when:
        request.method = method
        controller.createStudent()

        then:
        response.status == SC_METHOD_NOT_ALLOWED

        where:
        method << ['PATCH', 'DELETE', 'GET', 'PUT']
    }

    def "createStudent accepts POST requests"() {
        when:
        request.method = 'POST'
        controller.createStudent()

        then:
        response.status == SC_OK
    }

    @Unroll("createStudent #method is #isIsNot accepted")
    def "createStudent allowed #methods"() {
        given:
        request.method = method

        when:
        controller.createStudent()

        then:
        response.status == responseStatus

        where:
        method   | responseStatus        | isIsNot
        'POST'   | SC_OK                 | ""
        'GET'    | SC_METHOD_NOT_ALLOWED | 'NOT'
        'PUT'    | SC_METHOD_NOT_ALLOWED | 'NOT'
        'PATCH'  | SC_METHOD_NOT_ALLOWED | 'NOT'
        'DELETE' | SC_METHOD_NOT_ALLOWED | 'NOT'

    }
}