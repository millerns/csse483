'''
Created on Feb 13, 2015

@author: Nick
'''

import endpoints
import protorpc
from main import PARENT_KEY
from models import Weatherpic

@endpoints.api(name="weatherpics", version="v1", description="Weatherpics API")
class WeatherpicsApi(protorpc.remote.Service):
    @Weatherpic.method(path="weatherpic/insert", http_method="POST", name="weatherpic.insert")
    def weatherpic_insert(self, request):
        """ Insert a pic """
        if request.from_datastore:
            my_pic = request
        else:
            my_pic = Weatherpic(parent=PARENT_KEY, image_url=request.image_url, caption=request.caption)
        my_pic.put()
        return my_pic
    
    @Weatherpic.query_method(path="weatherpic/list", http_method="GET", name="weatherpic.list", query_fields=("limit", "order", "pageToken"))
    def weatherpic_list(self, query):
        """ Return all pics """
        return query
    
    @Weatherpic.method(request_fields=("entityKey",), path="weatherpic/delete/{entityKey}", http_method="DELETE", name="weatherpic.delete")
    def weatherpipc_delete(self, request):
        """ Delete the given pic if present """
        if not request.from_datastore:
            raise endpoints.NotFoundException("Weatherpic not found")
        request.key.delete()
        return Weatherpic(caption="deleted")
    
app = endpoints.api_server([WeatherpicsApi], restricted=False)