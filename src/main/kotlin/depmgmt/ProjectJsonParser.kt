package depmgmt

import com.google.gson.Gson

object ProjectJsonParser {

    fun parse(projectJsonText: String): ProjectJson {
        val projectJson = Gson().fromJson(projectJsonText, ProjectJson::class.java)
        return validate(projectJson)
    }

    private fun validate(projectJson: ProjectJson): ProjectJson {

        return projectJson
    }

}
