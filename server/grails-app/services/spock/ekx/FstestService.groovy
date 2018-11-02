package spock.ekx

import grails.gorm.transactions.Transactional
import org.apache.commons.lang.StringUtils

@Transactional
class FstestService {

    def Student saveStudent(String name, String email) {
        if(StringUtils.isBlank(name)) {
            throw new RuntimeException("name blank")
        }

        if(StringUtils.isBlank(email)) {
            throw new RuntimeException("email blank")
        }

        Student student = new Student(name: name, email: email)
        student.save()
        return student
    }

}
