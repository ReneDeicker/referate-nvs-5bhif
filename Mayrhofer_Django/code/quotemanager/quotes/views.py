from django.shortcuts import render
from rest_framework import viewsets, permissions

from .models import Quote, Person
from .serializers import QuoteSerializer, PersonSerializer


class QuoteView(viewsets.ModelViewSet):
    queryset = Quote.objects.all()
    serializer_class = QuoteSerializer
    permission_classes = [permissions.IsAuthenticated]


class PersonView(viewsets.ModelViewSet):
    queryset = Person.objects.all()
    serializer_class = PersonSerializer
