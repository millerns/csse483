'''
Created on Feb 11, 2015

@author: Nick
'''

import endpoints
import protorpc

from models import Student, Assignment, GradeEntry

WEB_CLIENT_ID = ""
ANDROID_CLIENT_ID = ""
IOS_CLIENT_ID = ""

@endpoints.api(name="graderecorder", version="v1", description="Grade Recorder API", hostname="millerns-grade-recorder.appspot.com",
               audiences=[], allowed_client_ids)
class GradeRecorderApi(protorpc.remote.Service):
    pass

app = endpoints.api_server([GradeRecorderApi], restricted = False)
