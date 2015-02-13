import endpoints
import protorpc
from main import PARENT_KEY
from models import MovieQuote

@endpoints.api(name="moviequotes", version="v1", description="Movie Quotes API")
class MovieQuotesApi(protorpc.remote.Service):
    
    @MovieQuote.method(path="moviequote/insert", http_method="POST", name="moviequote.insert")
    def moviequote_insert(self, request):
        """ Insert a quote """
        if request.from_datastore:
            my_quote = request
        else:
            my_quote = MovieQuote(parent=PARENT_KEY, quote=request.quote, movie=request.movie)
        my_quote.put()
        return my_quote
    
    @MovieQuote.query_method(path="moviequote/list", http_method="GET", name = "moviequote.list", query_fields=("limit", "order", "pageToken"))
    def moviequote_list(self, query):
        """ Return all the quotes """
        return query
        
    @MovieQuote.method(request_fields=("entityKey",), path="moviequote/delete/{entityKey}", http_method="DELETE", name="moviequote.delete")
    def moviequote_delete(self, request):
        """ Delete the given quote if present """
        if not request.from_datastore:
            raise endpoints.NotFoundException("movie quote not found")
        request.key.delete()
        return MovieQuote(quote="deleted")
        
app = endpoints.api_server([MovieQuotesApi], restricted=False)