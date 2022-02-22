import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class University{
    val studentArray = arrayListOf<Student>()
    val teacherArray = arrayListOf<Teacher>()

    fun createTestData(){
        val student1 = Student(1, "aaa")
        val student2 = Student(2, "bbb")
        val student3 = Student(3, "ccc")
        studentArray.add(student1)
        studentArray.add(student2)
        studentArray.add(student3)

        val course1 = Course(1,"a", LocalDateTime.now() )
        val course2 = Course(2,"b", LocalDateTime.now() )

        val mutableMapExam1 = mutableMapOf(1 to 18.0, 2 to 20.0, 3 to 15.0)
        val mutableMapExam2 = mutableMapOf(1 to 13.0, 2 to 14.0, 3 to 17.0)
        course1.addExam(LocalDate.now(), mutableMapExam1)
        course1.addExam(LocalDate.now(),mutableMapExam2)

        student1.courseArray.add(course1)
        student2.courseArray.add(course2)
        student3.courseArray.add(course2)

    }


}

data class Student(var id : Int, var name : String){
    val courseArray = arrayListOf<Course>()
    var average  = 0.0
    
    fun getGrade(course: Course): ArrayList<Double>{
        return course.getStudentGrade(id)
    }

}

class Course(var id : Int, var courseName : String, var finalExamDate : LocalDateTime){
    val examArray = arrayListOf<Exam>()

    fun addExam(date: LocalDate, gradsMutableMap: MutableMap<Int, Double>){
        val exam = Exam(id,this, date, gradsMutableMap)
        examArray.add(exam)
    }

    fun getStudentGrade(id: Int): ArrayList<Double>{
        val grades = arrayListOf<Double>()
       for( exam in examArray){
           val grade = exam.gradeMutableMap[id]
           grade?.let { grades.add(it) }
       }
        return grades
    }
}
class Exam(var id : Int, var course: Course,var date: LocalDate, var gradeMutableMap: MutableMap<Int,Double> )

data class Teacher(var id : Int, var name : String){
    val courseArray = arrayListOf<Course>()

}



fun main() {

    val uni1 = University()
    uni1.createTestData()
    println(uni1.studentArray[0].getGrade(uni1.studentArray[0].courseArray[0]))


}