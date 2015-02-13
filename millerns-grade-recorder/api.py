'''
Created on Feb 13, 2015

@author: Nick
'''

import protorpc
import endpoints
from models import Student, Assignment, GradeEntry
import main

WEB_CLIENT_ID="447073529171-o6arlahe2tup591ljl1rdjk62m73fbae.apps.googleusercontent.com"
ANDROID_CLIENT_ID="447073529171-i5q5nqhjtgacq3lg2db2sjiifop4uhv1.apps.googleusercontent.com"
IOS_CLIENT_ID = ""

@endpoints.api(name="graderecorder", version="v1", description="Grade Recorder API",
                audiences=[WEB_CLIENT_ID], 
                allowed_client_ids=[endpoints.API_EXPLORER_CLIENT_ID, WEB_CLIENT_ID,
                                    ANDROID_CLIENT_ID, IOS_CLIENT_ID])
class GradeRecorderApi(protorpc.remote.Service):
    @Student.query_method(user_required=True, query_fields=("limit", "pageToken"), name="student.list",
                          path="student/list",  http_method="GET")
    def student_list(self, query):
        """ list all the students for a given user """
        user = endpoints.get_current_user()
        students = Student.query(ancestor=main.get_parent_key(user)).order(Student.rose_username)
        return students
    
    @Assignment.query_method(user_required=True, query_fields=("limit", "pageToken"), name="assignment.list",
                          path="assignment/list",  http_method="GET")
    def assignment_list(self, query):
        """ List all the assignments owned by the user """
        user = endpoints.get_current_user()
        assignments = Assignment.query(ancestor=main.get_parent_key(user)).order(Assignment.name)
        return assignments
    
    @GradeEntry.query_method(user_required=True, query_fields=("limit", "order", "pageToken", "assignment_key"),
                             name="gradeentry.list", path="gradeentry/list/{assignment_key}",  http_method="GET")
    def gradeentry_list(self, query):
        """ List all the grade entries for the given assignment key """
        return query
    
    # Insert methods
    @Assignment.method(user_required=True, name="assignment.insert", path="assignment/insert",  http_method="POST")
    def assignment_insert(self, assignment):
        """ Add or update an assignment owned by the given user """
        if assignment.from_datastore:
            assignment_with_parent = assignment
        else: assignment_with_parent = Assignment(parent = main.get_parent_key(endpoints.get_current_user()), 
                                                  name = assignment.name)
        assignment_with_parent.put()
        return assignment_with_parent
    
    @GradeEntry.method(user_required=True, name="gradeentry.insert", path="gradeentry/insert",  http_method="POST")
    def gradeentry_insert(self, grade_entry):
        """ Add or update a grade entry for an assignment """
        if grade_entry.from_datastore:
            grade_entry_with_parent = grade_entry
        else: 
            student = grade_entry.student_key.get()
            grade_entry_with_parent = GradeEntry(parent = grade_entry.assignment_key, id = student.rose_username, 
                                                 score = grade_entry.score, student_key = grade_entry.student_key, 
                                                 assignment_key = grade_entry.assignment_key)
        grade_entry_with_parent.put()
        return grade_entry_with_parent    

    # Delete methods
    @Assignment.method(user_required=True, request_fields = ("entityKey",), name="assignment.delete",
                       path="assignment/delete/{entityKey}",  http_method="DELETE")
    def assignment_delete(self, assignment):
        """ Delete the assignment with the given key, plus asll the associated grade entries """
        if not assignment.from_datastore:
            raise endpoints.NotFoundException("No assignment found for the given key")
        children = GradeEntry.query(ancestor = assignment.key)
        for grade_entry in children:
            grade_entry.key.delete()
        assignment.key.delete()
        return Assignment(name="deleted")
    
    @GradeEntry.method(user_required=True, request_fields = ("entityKey",), name="gradeentry.delete",
                       path="gradeentry/delete/{entityKey}",  http_method="DELETE")
    def gradeentry_delete(self, grade_entry):
        """ Delete the grade entry with the given key """
        if not grade_entry.from_datastore:
            raise endpoints.NotFoundException("No grade entry found for the given key")
        grade_entry.key.delete()
        return GradeEntry()
        
           
app = endpoints.api_server([GradeRecorderApi], restricted = False)