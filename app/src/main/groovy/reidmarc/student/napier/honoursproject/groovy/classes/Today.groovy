package reidmarc.student.napier.honoursproject.groovy.classes

// Today.groovy class written by Jon Kerridge

import java.sql.Date

class Today {
    def now
    def year, month, day, monthAbbr, date, sqlDay

    def Today(){
        def months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec']
        now = Calendar.getInstance()
        year = now.get(Calendar.YEAR)
        month = now.get(Calendar.MONTH) + 1
        day = now.get(Calendar.DATE)
        monthAbbr = months[month-1]
        sqlDay = new Date(System.currentTimeMillis())
    }

    def getToday(){
        return [year, month, day, monthAbbr, sqlDay]
    }

    def getAbbrToday(){
        return  [day, monthAbbr ,year]
    }

}
