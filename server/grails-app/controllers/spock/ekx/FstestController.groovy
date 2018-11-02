package spock.ekx


import grails.rest.*
import grails.converters.*

class FstestController {
	static responseFormats = ['json', 'xml']

    static allowedMethods = [createStudent: 'POST',
                             update: 'PUT']

    FstestService fstestService
	

    def createStudent() {
        def req = request.JSON
        fstestService.saveStudent(req.name, req.email)
        render(status: 200)
    }

    def update() {
        print(request.method)
    }
}
