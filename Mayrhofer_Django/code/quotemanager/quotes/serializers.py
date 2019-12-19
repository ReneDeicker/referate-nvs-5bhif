from rest_framework import serializers

from .models import Quote, Person


class QuoteSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Quote
        fields = ('id', 'url', 'text', 'person')


class PersonSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Person
        fields = ('id', 'url', 'name')
