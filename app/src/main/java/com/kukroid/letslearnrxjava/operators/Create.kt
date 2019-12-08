package com.kukroid.letslearnrxjava.operators

import com.kukroid.letslearnrxjava.Student
import com.kukroid.letslearnrxjava.Util
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe

import kotlin.collections.ArrayList

class Create {

    fun operateCreate(): Observable<Student> {

        return Observable.create(ObservableOnSubscribe<Student> {
            var studentList= getStudents()
            for (student in studentList) {
                    it.onNext(student)

            }
            it.onComplete()
        })
    }

    fun getStudents(): ArrayList<Student>{
        val studentList: ArrayList<Student> = ArrayList()

        studentList.add(Student("Saurabh", "SaurabhKureja@live.com", 29))
        studentList.add(Student("Sakshi", "arora489@gmail.com", 29))

        return studentList

    }
}