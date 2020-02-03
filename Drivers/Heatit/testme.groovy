#!/usr/bin/env groovy
//testme.groovy
// Jakob test groovy app
println "Test listing object properties\n"

class Abc {

    def a
    def b

}

class Xyz extends Abc {
    def c
    def d
}

def myObj = new Xyz(c:1,d:2)
// def xyz = new Xyz(c:421,d:422)

// current child only!
void ShowGetMembers(Object obj) {
	println "\n:: listing metaClass methods start with 'get'"
	obj.metaClass.methods.findAll{it.declaringClass.name == obj.class.name}.each { 
		if(it.name.startsWith("get"))  {
			println it.name + " : " + obj.metaClass.invokeMethod(obj.class, obj, it.name, null, false, true)
		}
	}
}
ShowGetMembers(myObj)
//hubitat runtime error:
// Expression [MethodCallExpression] is not allowed: obj.metaClass.invokeMethod(obj.class, obj, it.name, null, false, true) at line number: 734

// groovytest...
def foobar="foobar"
def myname="jakob"
if (foobar!= null && myname=="jakob") {
	println "Jakob yourehere!"
}

// entire object hierarchy
void PrintAllMembers(Object obj) {
	println "\n:: All members of " + obj
	obj.properties.each { k,v -> println k+" : "+v}
}

PrintAllMembers(myObj)
// declaredFields getter (was not initially working!)
println "\n:: current class fields"

declaredFields = myObj.declaredFields.collect{it.name}
declaredFields.each { k,v -> println v}
