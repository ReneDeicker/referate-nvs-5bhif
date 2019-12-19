from django.urls import path, include
from rest_framework import routers

from .views import QuoteView, PersonView

router = routers.DefaultRouter()
router.register('quotes', QuoteView)
router.register('persons', PersonView)

urlpatterns = [
    path('', include(router.urls))
]
