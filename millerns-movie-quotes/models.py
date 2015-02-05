'''
Created on Feb 5, 2015

@author: Nick
'''
from endpoints_proto_datastore.ndb.model import EndpointsModel
from google.appengine.ext import ndb

class MovieQuote(EndpointsModel):
    ''' a movie quote and the title of the movie from which it came '''
    _message_fields_schema = ("entityKey", "quote", "movie", "last_touch_date_time")
    quote = ndb.StringProperty()
    movie = ndb.StringProperty()
    last_touch_date_time = ndb.DateTimeProperty(auto_now=True)