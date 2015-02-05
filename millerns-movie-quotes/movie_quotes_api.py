import endpoints
import protorpc
from models import MovieQuote

@endpoints.api(name="moviequotes", version="v1", description="Movie Quotes API")
class MovieQuotesApi(protorpc.remote.Service):
    
    @MovieQuote.method(path="moviequote/insert", name="moviequote.insert", http_method="POST")
    def moviequote_insert(self, request):
        my_quote = MovieQuote(parent=main_bootstrap.PARENT_KEY, quote=request.quote, movie=request.movie)
        my_quote.put()
        return my_quote
    
app = endpoints.api_server([MovieQuotesApi], restricted=False)