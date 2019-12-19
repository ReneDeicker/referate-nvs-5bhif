import json

from flask import Flask
from flask_restful import Resource, Api, marshal_with, fields

app = Flask(__name__)
api = Api(app)


class Person:
    def __init__(self, name, idnum=None):
        self.id = idnum
        self.name = name


class Quote:
    def __init__(self, text, person, idnum):
        self.id = idnum
        self.text = text
        self.person = person


persons = [
    Person("Erik Mayrhofer", 1),
    Person("Test Person", 2)
]

quotes = [
    Quote("Hello this is a quote", persons[1], 1),
    Quote("Hello this is a quote2", persons[0], 2),
    Quote("Hello this is a quote3", persons[1], 3),
]

persons_fields = {
    'id': fields.Integer,
    'name': fields.String
}
quote_fields = {
    'id': fields.Integer,
    'text': fields.String,
    'person': fields.Nested(nested=persons_fields)
}


class QuotesResource(Resource):
    @marshal_with(quote_fields)
    def get(self):
        return quotes


api.add_resource(QuotesResource, '/quotes/')

if __name__ == '__main__':
    app.run(debug=True)
