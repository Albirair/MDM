
# from dataclasses 
# import dataclass

# from flask 
# import Flask, jsonify, request

# from flask_sqlalchemy
# import SQLAlchemy

# from flask_marshmallow
# import Marshmallow

# from flask_migrate 
# import Migrate

# from flask_restless 
# import APIManager





# app = Flask(__name__)

# app.config['SQLALCHEMY_DATABASE_URI']
# = 'postgresql://postgres:postgres@localhost/postgres'

# # app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///db.db'

# app.config['SQLALCHEMY_TRACK_MODIFICATIONS']
# = True

# db = SQLAlchemy(app)



# manager = 
# APIManager(app,flask_sqlalchemy_db=db)



# migrate = 
# Migrate(app, db)



# ma = Marshmallow(app)





# class 
# GeographicSiteSchema(ma.Schema):

#     class 
# Meta:

#         fields = 
# ('id',
# 'name',
# 'code',
# 'GBID',
# 'ESID',
# 'on_air_date',
# 'description',
# 'status',
# 'baseType',

#                   'type',
# 'schemaLocation',
# 'geographicaddress',
# 'Calendars',
# 'Sites',
# 'Parties')





# SiteSchema = 
# GeographicSiteSchema()

# SiteSchemas = 
# GeographicSiteSchema(many=True)





# class 
# GeographicAddressSchema(ma.Schema):

#     class 
# Meta:

#         fields = 
# ('id',
# 'href',
# 'streetNr',
# 'streetNrSuffix',
# 'streetNrLast',
# 'streetName',
# 'streetType',
# 'streetSuffix',
# 'postcode',

#                   'geographicsubaddress',
# 'geographiclocation',
# 'continent',
# 'country',
# 'state',
# 'city',
# 'locality',
# 'block')





# AddressSchema = 
# GeographicAddressSchema()

# AddressSchemas = 
# GeographicAddressSchema(many=True)





# class 
# GeographicLocationSchema(ma.Schema):

#     class 
# Meta:

#         fields = 
# ('id',
# 'href',
# 'name',
# 'geometryType',
# 'accuracy',

#                   'spatialRef',
# 'geographicpoints',
# 'type',
# 'schemaLocation')





# LocationSchema = 
# GeographicLocationSchema()

# LocationSchemas = 
# GeographicLocationSchema(many=True)





# class 
# GeographicSubAddressSchema(ma.Schema):

#     class 
# Meta:

#         fields = 
# ('id',
# 'href',
# 'subAddressType',
# 'subUnitType',
# 'subUnitNumber',
# 'levelType',
# 'levelNumber',

#                   'buildingName',
# 'privateStreetName',
# 'privateStreetNumber',
# 'type',
# 'schemaLocation')





# SubAddressSchema = 
# GeographicSubAddressSchema()

# SubAddressSchemas = 
# GeographicSubAddressSchema(many=True)





# class 
# GeographicPointSchema(ma.Schema):

#     class 
# Meta:

#         fields = 
# ('id',
# 'x',
# 'y',
# 'z')





# GeoPointSchema = 
# GeographicPointSchema()

# GeoPointSchemas = 
# GeographicPointSchema(many=True)





# class 
# CalendarPeriodSchema(ma.Schema):

#     class 
# Meta:

#         fields = 
# ('id',
# 'status',
# 'day',
# 'timeZone',
# 'Hourperiods')





# CalendarSchema = 
# CalendarPeriodSchema()

# CalendarSchemas = 
# CalendarPeriodSchema(many=True)





# class 
# HourPeriodSchema(ma.Schema):

#     class 
# Meta:

#         fields = 
# ('id',
# 'startHour',
# 'endHour')





# HourSchema = 
# HourPeriodSchema()

# HourSchemas = 
# HourPeriodSchema(many=True)





# class 
# ContinentSchema(ma.Schema):

#     class 
# Meta:

#         fields = 
# ('id',
# 'name',
# 'countries')





# continentSchema = 
# ContinentSchema()

# continentSchemas = 
# ContinentSchema(many=True)





# class 
# CountrySchema(ma.Schema):

#     class 
# Meta:

#         fields = 
# ('id',
# 'name',
# 'code',
# 'states',
# 'Continent')





# countrySchema = 
# CountrySchema()

# countrySchemas = 
# CountrySchema(many=True)





# class 
# StateSchema(ma.Schema):

#     class 
# Meta:

#         fields = 
# ('id',
# 'name',
# 'code',
# 'country',
# 'cities')





# stateSchema = 
# StateSchema()

# stateSchemas = 
# StateSchema(many=True)





# class 
# CitySchema(ma.Schema):

#     class 
# Meta:

#         fields = 
# ('id',
# 'name',
# 'is_Capital',
# 'localities')





# citySchema = 
# CitySchema()

# citySchemas = 
# CitySchema(many=True)





# class 
# LocalitySchema(ma.Schema):

#     class 
# Meta:

#         fields = 
# ('id',
# 'name',
# 'state',
# 'blocks',
# 'city')





# localitySchema = 
# LocalitySchema()

# localitySchemas = 
# LocalitySchema(many=True)





# class 
# Block_HaySchema(ma.Schema):

#     class 
# Meta:

#         fields = 
# ('id',
# 'name',
# 'state')





# block_haySchema = 
# Block_HaySchema()

# block_haySchemas = 
# Block_HaySchema(many=True)





# class 
# ZoneSchema(ma.Schema):

#     class 
# Meta:

#         fields = 
# ('id',
# 'name',
# 'code',
# 'offices')





# zoneSchema = 
# ZoneSchema()

# zoneSchemas = 
# ZoneSchema(many=True)





# class 
# OfficeSchema(ma.Schema):

#     class 
# Meta:

#         fields = 
# ('id',
# 'name',
# 'code',
# 'zone_id',
# 'clusters',
# 'address')





# officeSchema = 
# OfficeSchema()

# officeSchemas = 
# OfficeSchema(many=True)





# class 
# ClusterSchema(ma.Schema):

#     class 
# Meta:

#         fields = 
# ('id',
# 'name',
# 'office_id')





# clusterSchema = 
# ClusterSchema()

# clusterSchemas = 
# ClusterSchema(many=True)



# #######################################################################################





# @dataclass

# class 
# Role(db.Model):

#     id:
# int = db.Column(db.Integer,
# primary_key=True,
# autoincrement=True)

#     name: 
# str = db.Column(db.String(20))





# @dataclass

# class 
# SubRole(db.Model):

#     id:
# int = db.Column(db.Integer,
# primary_key=True,
# autoincrement=True)

#     name: 
# str = db.Column(db.String(20))

#     role_id: 
# int = db.Column(db.Integer,
#  db.ForeignKey(Role.id))

#     RoleRelation = db.relationship('Role',
# backref='subroles',
# uselist=True)





# @dataclass

# class 
# SiteRole(db.Model):

#     id:
# int = db.Column(db.Integer,
# primary_key=True,
# autoincrement=True)

#     site_id: 
# int = db.Column(db.Integer,
#  db.ForeignKey('GeographicSite.id'))

#     role_id: 
# int = db.Column(db.Integer,
#  db.ForeignKey(Role.id))

#     GeographicSiteRelation = db.relationship(

#         'GeographicSite',
# backref='siteroles',
# uselist=True)





# @dataclass

# class 
# Zone(db.Model):

#     id:
# int = db.Column(db.Integer,
# primary_key=True,
# autoincrement=True)

#     name: 
# str = db.Column(db.String(20))

#     code: 
# str = db.Column(db.String(20))





# @dataclass

# class 
# Office(db.Model):

#     id:
# int = db.Column(db.Integer,
# primary_key=True,
# autoincrement=True)

#     name: 
# str = db.Column(db.String(20))

#     address: 
# int = db.Column(db.Integer,
#  db.ForeignKey('GeographicAddress.id'))

#     zone_id: 
# int = db.Column(db.Integer,
#  db.ForeignKey(Zone.id))



#     ZoneRelation = db.relationship(

#         'Zone',
# backref='offices',
# uselist=True)





# @dataclass

# class 
# Cluster(db.Model):

#     id:
# int = db.Column(db.Integer,
# primary_key=True,
# autoincrement=True)

#     name: 
# str = db.Column(db.String(20))



#     office_id: 
# int = db.Column(db.Integer,
#  db.ForeignKey(Office.id))



#     OfficeRelation = db.relationship(

#         'Office',
# backref='clusters',
# uselist=True)





# @dataclass

# class 
# GeographicSite(db.Model):

#     __tablename__ = 
# 'GeographicSite'

#     id:
# int = db.Column(db.Integer,
# primary_key=True,
# autoincrement=True)

#     name: 
# str = db.Column(db.String(120))

#     href: 
# str = db.Column(db.String(200))

#     code: 
# str = db.Column(db.String(120))

#     GBID: 
# str = db.Column(db.String(120))

#     ESID: 
# str = db.Column(db.String(120))

#     Latitude: 
# str = db.Column(db.String(120))

#     Longitude: 
# str = db.Column(db.String(120))

#     Elevation: 
# str = db.Column(db.String(120))

    

#     on_air_date = db.Column(db.DateTime)

#     description: 
# str = db.Column(db.String(120))

#     status: 
# str = db.Column(db.String(20))



#     baseType: 
# str = db.Column(db.DateTime)

#     type:
# str = db.Column(db.String(120))

#     schemaLocation: 
# str = db.Column(db.String(120))



#     # FK

#     address: 
# int = db.Column(

#         db.Integer, db.ForeignKey('GeographicAddress.id'))

#     siteRelation: 
# int = db.Column(

#         db.Integer, db.ForeignKey('SiteRelationship.id'))

#     # resourceRelation: int = db.Column(

#     #     db.Integer, db.ForeignKey('ResourceRelationship.id'))

#     calendar: 
# int = db.Column(db.Integer,
#  db.ForeignKey('CalendarPeriod.id'))

#     geographicLocation: 
# int = db.Column(

#         db.Integer, db.ForeignKey('GeographicLocation.id'))

#     relatedParty: 
# int = db.Column(

#         db.Integer, db.ForeignKey('RelatedPartyRef.id'))





# @dataclass

# class 
# RelatedPartyRef(db.Model):

#     __tablename__ = 
# 'RelatedPartyRef'

#     id:
# int = db.Column(db.Integer,
# primary_key=True,
# autoincrement=True)



#     href: 
# str = db.Column(db.String(120))

#     name: 
# str = db.Column(db.String(120))

#     role: 
# str = db.Column(db.String(120))

#     type:
# str = db.Column(db.String(120))

#     validFor: 
# str = db.Column(db.String(120))



#     GeographicSiteRelation = db.relationship(

#         'GeographicSite',
# backref='Parties',
# uselist=True)





# @dataclass

# class 
# SiteRelationship(db.Model):

#     __tablename__ = 
# 'SiteRelationship'

#     # __table_args__ = {'extend_existing': True}

#     id:
# int = db.Column(db.Integer,
# primary_key=True,
# autoincrement=True)



#     href: 
# str = db.Column(db.String(120))

#     type:
# str = db.Column(db.String(120))

#     role: 
# str = db.Column(db.String(120))

#     validFor: 
# str = db.Column(db.String(120))



#     GeographicSiteRelation = db.relationship(

#         'GeographicSite',
# backref='Sites',
# uselist=True)





# # @dataclass

# # class ResourceRelationship(db.Model):

# #     __tablename__ = 'ResourceRelationship'

# #     id: int = db.Column(db.Integer, primary_key=True, autoincrement=True)

# #     name: str = db.Column(db.String(50))

# #     href: str = db.Column(db.String(120))

# #     type: str = db.Column(db.String(120))

# #     validFor: str = db.Column(db.String(120))



# #     GeographicSiteRelation = db.relationship(

# #         'GeographicSite', backref='Resources', uselist=True)





# @dataclass

# class 
# HourPeriod(db.Model):  #
#  type:ignore

#     __tablename__ = 
# 'HourPeriod'

#     id:
# int = db.Column(db.Integer,
# primary_key=True,
# autoincrement=True)



#     startHour: 
# str = db.Column(db.String(120))

#     endHour: 
# str = db.Column(db.String(120))



#     CalendarPeriodRelation = db.relationship(

#         'CalendarPeriod',
# backref='Hourperiods',
# uselist=True)





# @dataclass

# class 
# CalendarPeriod(db.Model):

#     __tablename__ = 
# 'CalendarPeriod'

#     id:
# int = db.Column(db.Integer,
# primary_key=True,
# autoincrement=True)



#     status: 
# str = db.Column(db.String(20))

#     day: 
# str = db.Column(db.String(120))

#     timeZone: 
# str = db.Column(db.String(120))

#     hourPeriod: 
# int = db.Column(db.Integer,
#  db.ForeignKey('HourPeriod.id'))



#     GeographicSiteRelation = db.relationship(

#         'GeographicSite',
# backref='Calendars',
# uselist=True)





# @dataclass

# class 
# GeographicAddress(db.Model):

#     __tablename__ = 
# 'GeographicAddress'

#     id:
# int = db.Column(db.Integer,
# primary_key=True,
# autoincrement=True)

#     href: 
# str = db.Column(db.String(120))

#     streetNr: 
# str = db.Column(db.String(120))

#     streetNrSuffix: 
# str = db.Column(db.String(80))

#     streetNrLast: 
# str = db.Column(db.String(120))

#     streetName: 
# str = db.Column(db.String(120))

#     streetType: 
# str = db.Column(db.String(120))

#     streetSuffix: 
# str = db.Column(db.String(120))

#     postcode: 
# str = db.Column(db.String(120))



#     GeographicSubAddress: 
# int = db.Column(

#         db.Integer, db.ForeignKey('GeographicSubAddress.id'))

#     GeographicLocation: 
# int = db.Column(

#         db.Integer, db.ForeignKey('GeographicLocation.id'))

#     Continent: 
# int = db.Column(db.Integer,
#  db.ForeignKey('Continent.id'))

#     Country: 
# int = db.Column(db.Integer,
#  db.ForeignKey('Country.id'))

#     State: 
# int = db.Column(db.Integer,
#  db.ForeignKey('State.id'))

#     City: 
# int = db.Column(db.Integer,
#  db.ForeignKey('City.id'))

#     Locality: 
# int = db.Column(db.Integer,
#  db.ForeignKey('Locality.id'))

#     Hay: 
# int = db.Column(db.Integer,
#  db.ForeignKey('Block_Hay.id'))



#     GeographicSiteRelation = db.relationship(

#         'GeographicSite',
# backref='geographicaddress',
# uselist=False)





# @dataclass

# class 
# GeographicSubAddress(db.Model):

#     __tablename__ = 
# 'GeographicSubAddress'



#     id:
# int = db.Column(db.Integer,
# primary_key=True,
# autoincrement=True)

#     href: 
# str = db.Column(db.String(120))

#     subAddressType: 
# str = db.Column(db.String(120))

#     subUnitType: 
# str = db.Column(db.String(80))

#     subUnitNumber: 
# str = db.Column(db.String(120))

#     levelType: 
# str = db.Column(db.String(120))

#     levelNumber: 
# str = db.Column(db.String(120))

#     buildingName: 
# str = db.Column(db.String(120))

#     privateStreetName: 
# str = db.Column(db.String(120))

#     privateStreetNumber: 
# str = db.Column(db.String(120))

#     type:
# str = db.Column(db.String(120))

#     schemaLocation: 
# str = db.Column(db.String(120))



#     GeographicAddressRelation = db.relationship(

#         'GeographicAddress',
# backref='geographicsubaddress',
# uselist=True)





# @dataclass

# class 
# GeographicPoint(db.Model):

#     __tablename__ = 
# 'GeographicPoint'

#     id:
# int = db.Column(db.Integer,
# primary_key=True,
# autoincrement=True)



#     x: str
# = db.Column(db.String(120))

#     y: str
# = db.Column(db.String(120))

#     z: str
# = db.Column(db.String(120))



#     GeographicLocationRelation = db.relationship(

#         'GeographicLocation',
# backref='geographicpoints',
# uselist=True)





# @dataclass

# class 
# GeographicLocation(db.Model):

#     __tablename__ = 
# 'GeographicLocation'

#     id:
# int = db.Column(db.Integer,
# primary_key=True,
# autoincrement=True)



#     href: 
# str = db.Column(db.String(120))

#     name: 
# str = db.Column(db.String(120))

#     geometryType: 
# str = db.Column(db.String(120))

#     accuracy: 
# str = db.Column(db.String(80))

#     spatialRef: 
# str = db.Column(db.String(120))

#     geometry = db.Column(db.Integer,
#  db.ForeignKey('GeographicPoint.id'))

#     type:
# str = db.Column(db.String(120))

#     schemaLocation: 
# str = db.Column(db.String(120))

#     GeographicAddressRelation = db.relationship(

#         'GeographicAddress',
# backref='geographiclocation',
# uselist=False)





# @dataclass

# class 
# Continent(db.Model):

#     __tablename__ = 
# 'Continent'



#     id:
# int = db.Column(db.Integer,
# primary_key=True,
# autoincrement=True)

#     name: 
# str = db.Column(db.String(50),
# unique=True)

#     GeographicAddressRelation = db.relationship(

#         'GeographicAddress',
# backref='continent',
# uselist=False)





# @dataclass

# class 
# Country(db.Model):

#     __tablename__ = 
# 'Country'

#     id:
# int = db.Column(db.Integer,
# primary_key=True,
# autoincrement=True)



#     name: 
# str = db.Column(db.String(50),
# unique=True)

#     code: 
# str = db.Column(db.String(50),
# unique=True)

#     Continent: 
# int = db.Column(db.Integer,
#  db.ForeignKey('Continent.id'))

#     ContinentRelation = db.relationship(

#         'Continent',
# backref='countries',
# uselist=True)



#     GeographicAddressRelation = db.relationship(

#         'GeographicAddress',
# backref='country',
# uselist=False)





# @dataclass

# class 
# State(db.Model):

#     __tablename__ = 
# 'State'

#     id:
# int = db.Column(db.Integer,
# primary_key=True,
# autoincrement=True)



#     name: 
# str = db.Column(db.String(50),
# unique=True)

#     code: 
# str = db.Column(db.String(50),
# unique=True)

#     country: 
# int = db.Column(db.Integer,
#  db.ForeignKey('Country.id'))

#     zone: 
# int = db.Column(db.Integer,
#  db.ForeignKey(Zone.id))



#     CountryRelation = db.relationship(

#         'Country',
# backref='states',
# uselist=True)



#     GeographicAddressRelation = db.relationship(

#         'GeographicAddress',
# backref='state',
# uselist=False)



#     ZoneRelation = db.relationship(

#         'Zone',
# backref='states',
# uselist=True)





# @dataclass

# class 
# City(db.Model):

#     __tablename__ = 
# 'City'

#     id:
# int = db.Column(db.Integer,
# primary_key=True,
# autoincrement=True)



#     name: 
# str = db.Column(db.String(50))

#     is_Capital: 
# bool = db.Column(db.Boolean,
# default=False)

#     state: 
# int = db.Column(db.Integer,
#  db.ForeignKey('State.id'))



#     StateRelation = db.relationship(

#         'State',
# backref='cities',
# uselist=True)



#     GeographicAddressRelation = db.relationship(

#         'GeographicAddress',
# backref='city',
# uselist=False)





# @dataclass

# class 
# Locality(db.Model):

#     __tablename__ = 
# 'Locality'

#     __table_args__ = 
# {'extend_existing':
# True}

#     id:
# int = db.Column(db.Integer,
# primary_key=True,
# autoincrement=True)



#     state: 
# str = db.Column(db.String(100))

#     name: 
# str = db.Column(db.String(100))

#     city: 
# int = db.Column(db.Integer,
#  db.ForeignKey('City.id'))



#     CityRelation = db.relationship(

#         'City',
# backref='localities',
# uselist=True)



#     GeographicAddressRelation = db.relationship(

#         'GeographicAddress',
# backref='locality',
# uselist=False)





# @dataclass

# class 
# Block_Hay(db.Model):

#     __tablename__ = 
# 'Block_Hay'

#     id:
# int = db.Column(db.Integer,
# primary_key=True,
# autoincrement=True)



#     state: 
# str = db.Column(db.String(100))

#     name: 
# str = db.Column(db.String(100))

#     locality: 
# int = db.Column(db.Integer,
#  db.ForeignKey('Locality.id'))



#     LocalityRelation = db.relationship(

#         'Locality',
# backref='blocks',
# uselist=True)



#     GeographicAddressRelation = db.relationship(

#         'GeographicAddress',
# backref='block',
# uselist=False)





# ###########################geographicSite##############################

# @app.get('/geographicSiteManagement/v1/geographicSite')

# def 
# fetchAllGeographicSites():

#     sites = GeographicSite.query.all()

#     result = SiteSchemas.dump(sites)

#     return 
# jsonify(result),
# 200





# @app.get('/geographicSiteManagement/v1/geographicSite/<id>')

# def 
# fetchOneGeographicSites(id):

#     obj = GeographicSite.query.get(int(id))

#     if obj:

#         result = SiteSchema.dump(obj)

#         return 
# jsonify(result),
# 200

#     return 
# 'Not Found',
# 404





# @app.put('/geographicSiteManagement/v1/geographicSite/<id>')

# def 
# updateGeographicSite(id):

#     obj = GeographicSite.query.get(int(id))

#     if 
# (obj):

#         # obj1 = GeographicSite(**request.json)

#         if request.json:

#             obj.name 
# = request.json['name']

#             obj.href 
# = request.json['href']

#             obj.code 
# = request.json['code']

#             obj.GBID 
# = request.json['GBID']

#             obj.ESID 
# = request.json['ESID']

#             obj.on_air_date 
# = request.json['on_air_date']

#             obj.description 
# = request.json['description']

#             obj.status 
# = request.json['status']

#             obj.baseType 
# = request.json['baseType']

#             obj.type 
# = request.json['type']

#             obj.schemaLocation 
# = request.json['schemaLocation']



#             db.session.commit()

#             return 
# jsonify(obj),
# 200

#     return 
# 'not found',
# 404





# @app.delete('/geographicSiteManagement/v1/geographicSite/<id>')

# def 
# deleteGeographicSite(id):

#     obj = GeographicSite.query.get(int(id))

#     if 
# (obj):

#         db.session.delete(obj)

#         db.session.commit()

#         return 
# 'Successful Removal',
# 204

#     return 
# 'Not Found',
# 404





# @app.post('/geographicSiteManagement/v1/geographicSite')

# def 
# saveGeographicSite():

#     obj: GeographicSite 
# = GeographicSite(**request.json)

#     db.session.add(obj)

#     db.session.commit()

#     obj.href 
# = f'/geographicSiteManagement/v1/geographicSite/{obj.id}'

#     db.session.commit()

#     return 
# jsonify(obj),
# 200



# ###########################geographicAddress##############################





# @app.get('/geographicSiteManagement/v1/geographicAddress')

# def 
# fetchAllGeographicAddress():

#     sites = GeographicAddress.query.all()

#     result = AddressSchemas.dump(sites)

#     return 
# jsonify(result),
# 200





# @app.get('/geographicSiteManagement/v1/geographicAddress/<id>')

# def 
# fetchOneGeographicAddress(id):

#     obj = GeographicAddress.query.get(int(id))

#     if obj:

#         result = AddressSchema.dump(obj)

#         return 
# jsonify(result),
# 200

#     return 
# 'Not Found',
# 404





# @app.put('/geographicSiteManagement/v1/geographicAddress/<id>')

# def 
# updateGeographicAddress(id):

#     obj = GeographicAddress.query.get(int(id))

#     if 
# (obj):

#         if request.json:

#             obj.streetNr 
# = request.json['streetNr']

#             obj.streetNrSuffix 
# = request.json['streetNrSuffix']

#             obj.streetNrLast 
# = request.json['streetNrLast']

#             obj.streetName 
# = request.json['streetName']

#             obj.streetType 
# = request.json['streetType']

#             obj.streetSuffix 
# = request.json['streetSuffix']

#             obj.postcode 
# = request.json['postcode']



#             db.session.commit()

#             return 
# jsonify(obj),
# 200

#     return 
# 'not found',
# 404





# @app.delete('/geographicSiteManagement/v1/geographicAddress/<id>')

# def 
# deletegeographicAddress(id):

#     obj = GeographicAddress.query.get(int(id))

#     if 
# (obj):

#         db.session.delete(obj)

#         db.session.commit()

#         return 
# 'Successful Removal',
# 204

#     return 
# 'Not Found',
# 404





# @app.post('/geographicSiteManagement/v1/geographicAddress')

# def 
# savegeographicAddress():

#     obj: GeographicAddress 
# = GeographicAddress(**request.json)

#     db.session.add(obj)

#     db.session.commit()

#     obj.href 
# = f'/geographicSiteManagement/v1/geographicAddress/{obj.id}'

#     db.session.commit()

#     return 
# jsonify(obj),
# 200



# ###########################GeographicLocation##############################





# @app.get('/geographicSiteManagement/v1/geographiclocation')

# def 
# fetchAllGeographiclocation():

#     sites = GeographicLocation.query.all()

#     result = LocationSchemas.dump(sites)

#     return 
# jsonify(result),
# 200





# @app.get('/geographicSiteManagement/v1/geographiclocation/<id>')

# def 
# fetchOneGeographicLocation(id):

#     obj = GeographicLocation.query.get(int(id))

#     if obj:

#         result = LocationSchema.dump(obj)

#         return 
# jsonify(result),
# 200

#     return 
# 'Not Found',
# 404





# @app.put('/geographicSiteManagement/v1/geographiclocation/<id>')

# def 
# updateGeographicLocation(id):

#     obj = GeographicLocation.query.get(int(id))

#     if 
# (obj):

#         if request.json:

#             obj.name 
# = request.json['name']

#             obj.geometryType 
# = request.json['geometryType']

#             obj.accuracy 
# = request.json['accuracy']

#             obj.spatialRef 
# = request.json['spatialRef']



#             db.session.commit()

#             return 
# jsonify(obj),
# 200

#     return 
# 'not found',
# 404





# @app.delete('/geographicSiteManagement/v1/geographiclocation/<id>')

# def 
# deleteGeographicLocation(id):

#     obj = GeographicLocation.query.get(int(id))

#     if 
# (obj):

#         db.session.delete(obj)

#         db.session.commit()

#         return 
# 'Successful Removal',
# 204

#     return 
# 'Not Found',
# 404





# @app.post('/geographicSiteManagement/v1/geographiclocation')

# def 
# saveGeographicLocation():

#     obj: GeographicLocation 
# = GeographicLocation(**request.json)

#     db.session.add(obj)

#     db.session.commit()

#     obj.href 
# = f'/geographicSiteManagement/v1/geographiclocation/{obj.id}'

#     db.session.commit()

#     return 
# jsonify(obj),
# 200



# ###########################GeographicSubAddress##############################





# @app.get('/geographicSiteManagement/v1/geographicsubaddress')

# def 
# fetchAllGeographicSubAddress():

#     sites = GeographicSubAddress.query.all()

#     result = SubAddressSchemas.dump(sites)

#     return 
# jsonify(result),
# 200





# @app.get('/geographicSiteManagement/v1/geographicsubaddress/<id>')

# def 
# fetchOneGeographicSubAddress(id):

#     obj = GeographicSubAddress.query.get(int(id))

#     if obj:

#         result = SubAddressSchema.dump(obj)

#         return 
# jsonify(result),
# 200

#     return 
# 'Not Found',
# 404





# @app.put('/geographicSiteManagement/v1/geographicsubaddress/<id>')

# def 
# updateGeographicSubAddress(id):

#     obj = GeographicSubAddress.query.get(int(id))

#     if 
# (obj):

#         if request.json:

#             obj.subAddressType 
# = request.json['subAddressType']

#             obj.subUnitType 
# = request.json['subUnitType']

#             obj.subUnitNumber 
# = request.json['subUnitNumber']

#             obj.levelType 
# = request.json['levelType']

#             obj.levelNumber 
# = request.json['levelNumber']

#             obj.buildingName 
# = request.json['buildingName']

#             obj.privateStreetName 
# = request.json['privateStreetName']

#             obj.privateStreetNumber 
# = request.json['privateStreetNumber']

#             obj.type 
# = request.json['type']

#             obj.schemaLocation 
# = request.json['schemaLocation']



#             db.session.commit()

#             return 
# jsonify(obj),
# 200

#     return 
# 'not found',
# 404





# @app.delete('/geographicSiteManagement/v1/geographicsubaddress/<id>')

# def 
# deleteGeographicSubAddress(id):

#     obj = GeographicSubAddress.query.get(int(id))

#     if 
# (obj):

#         db.session.delete(obj)

#         db.session.commit()

#         return 
# 'Successful Removal',
# 204

#     return 
# 'Not Found',
# 404





# @app.post('/geographicSiteManagement/v1/geographicsubaddress')

# def 
# saveGeographicSubAddress():

#     obj: GeographicSubAddress 
# = GeographicSubAddress(**request.json)

#     db.session.add(obj)

#     db.session.commit()

#     obj.href 
# = f'/geographicSiteManagement/v1/geographicsubaddress/{obj.id}'

#     db.session.commit()

#     return 
# jsonify(obj),
# 200



# ###########################GeographicSubAddress##############################





# @app.get('/geographicSiteManagement/v1/geographicpoint')

# def 
# fetchAllGeographicPoint():

#     sites = GeographicPoint.query.all()

#     result = GeoPointSchemas.dump(sites)

#     return 
# jsonify(result),
# 200





# @app.get('/geographicSiteManagement/v1/geographicpoint/<id>')

# def 
# fetchOneGeographicPoint(id):

#     obj = GeographicPoint.query.get(int(id))

#     if obj:

#         result = GeoPointSchema.dump(obj)

#         return 
# jsonify(result),
# 200

#     return 
# 'Not Found',
# 404





# @app.put('/geographicSiteManagement/v1/geographicpoint/<id>')

# def 
# updateGeographicPoint(id):

#     obj = GeographicPoint.query.get(int(id))

#     if 
# (obj):

#         if request.json:

#             obj.x 
# = request.json['x']

#             obj.y 
# = request.json['y']

#             obj.z 
# = request.json['z']



#             db.session.commit()

#             return 
# jsonify(obj),
# 200

#     return 
# 'not found',
# 404





# @app.delete('/geographicSiteManagement/v1/geographicpoint/<id>')

# def 
# deleteGeographicPoint(id):

#     obj = GeographicPoint.query.get(int(id))

#     if 
# (obj):

#         db.session.delete(obj)

#         db.session.commit()

#         return 
# 'Successful Removal',
# 204

#     return 
# 'Not Found',
# 404





# @app.post('/geographicSiteManagement/v1/geographicpoint')

# def 
# saveGeographicPoint():

#     obj: GeographicPoint 
# = GeographicPoint(**request.json)

#     db.session.add(obj)

#     db.session.commit()

#     obj.href 
# = f'/geographicSiteManagement/v1/geographicpoint/{obj.id}'

#     db.session.commit()

#     return 
# jsonify(obj),
# 200

# ###########################CalendarPeriod##############################





# @app.get('/geographicSiteManagement/v1/calendarperiod')

# def 
# fetchAllCalendarPeriod():

#     sites = CalendarPeriod.query.all()

#     result = CalendarSchemas.dump(sites)

#     return 
# jsonify(result),
# 200





# @app.get('/geographicSiteManagement/v1/calendarperiod/<id>')

# def 
# fetchOneCalendarPeriod(id):

#     obj = CalendarPeriod.query.get(int(id))

#     if obj:

#         result = CalendarSchema.dump(obj)

#         return 
# jsonify(result),
# 200

#     return 
# 'Not Found',
# 404





# @app.put('/geographicSiteManagement/v1/calendarperiod/<id>')

# def 
# updateCalendarPeriod(id):

#     obj = CalendarPeriod.query.get(int(id))

#     if 
# (obj):

#         if request.json:

#             obj.status 
# = request.json['status']

#             obj.day 
# = request.json['day']

#             obj.timeZone 
# = request.json['timeZone']



#             db.session.commit()

#             return 
# jsonify(obj),
# 200

#     return 
# 'not found',
# 404





# @app.delete('/geographicSiteManagement/v1/calendarperiod/<id>')

# def 
# deleteCalendarPeriod(id):

#     obj = CalendarPeriod.query.get(int(id))

#     if 
# (obj):

#         db.session.delete(obj)

#         db.session.commit()

#         return 
# 'Successful Removal',
# 204

#     return 
# 'Not Found',
# 404





# @app.post('/geographicSiteManagement/v1/calendarperiod')

# def 
# saveCalendarPeriod():

#     obj: CalendarPeriod 
# = CalendarPeriod(**request.json)

#     db.session.add(obj)

#     db.session.commit()

#     obj.href 
# = f'/geographicSiteManagement/v1/calendarperiod/{obj.id}'

#     db.session.commit()

#     return 
# jsonify(obj),
# 200



# ###########################CalendarPeriod##############################





# @app.get('/geographicSiteManagement/v1/hourperiod')

# def 
# fetchAllHourPeriod():

#     sites = HourPeriod.query.all()

#     result = HourSchemas.dump(sites)

#     return 
# jsonify(result),
# 200





# @app.get('/geographicSiteManagement/v1/hourperiod/<id>')

# def 
# fetchOneHourPeriod(id):

#     obj = HourPeriod.query.get(int(id))

#     if obj:

#         result = HourSchema.dump(obj)

#         return 
# jsonify(result),
# 200

#     return 
# 'Not Found',
# 404





# @app.put('/geographicSiteManagement/v1/hourperiod/<id>')

# def 
# updateHourPeriod(id):

#     obj = HourPeriod.query.get(int(id))

#     if 
# (obj):

#         if request.json:

#             obj.startHour 
# = request.json['startHour']

#             obj.endHour 
# = request.json['endHour']



#             db.session.commit()

#             return 
# jsonify(obj),
# 200

#     return 
# 'not found',
# 404





# @app.delete('/geographicSiteManagement/v1/hourperiod/<id>')

# def 
# deleteHourPeriod(id):

#     obj = HourPeriod.query.get(int(id))

#     if 
# (obj):

#         db.session.delete(obj)

#         db.session.commit()

#         return 
# 'Successful Removal',
# 204

#     return 
# 'Not Found',
# 404





# @app.post('/geographicSiteManagement/v1/hourperiod')

# def 
# saveHourPeriod():

#     obj: HourPeriod 
# = HourPeriod(**request.json)

#     db.session.add(obj)

#     db.session.commit()

#     obj.href 
# = f'/geographicSiteManagement/v1/hourperiod/{obj.id}'

#     db.session.commit()

#     return 
# jsonify(obj),
# 200



# ###########################Continent##############################





# @app.get('/geographicSiteManagement/v1/continent')

# def 
# fetchAllContinent():

#     sites = Continent.query.all()

#     result = continentSchemas.dump(sites)

#     return 
# jsonify(result),
# 200





# @app.get('/geographicSiteManagement/v1/continent/<id>')

# def 
# fetchOneContinent(id):

#     obj = Continent.query.get(int(id))

#     if obj:

#         result = continentSchema.dump(obj)

#         return 
# jsonify(result),
# 200

#     return 
# 'Not Found',
# 404





# @app.put('/geographicSiteManagement/v1/continent/<id>')

# def 
# updateContinent(id):

#     obj = Continent.query.get(int(id))

#     if 
# (obj):

#         if request.json:

#             obj.name 
# = request.json['name']



#             db.session.commit()

#             return 
# jsonify(obj),
# 200

#     return 
# 'not found',
# 404





# @app.delete('/geographicSiteManagement/v1/continent/<id>')

# def 
# deleteContinent(id):

#     obj = Continent.query.get(int(id))

#     if 
# (obj):

#         db.session.delete(obj)

#         db.session.commit()

#         return 
# 'Successful Removal',
# 204

#     return 
# 'Not Found',
# 404





# @app.post('/geographicSiteManagement/v1/continent')

# def 
# saveContinent():

#     obj: Continent 
# = Continent(**request.json)

#     db.session.add(obj)

#     db.session.commit()

#     obj.href 
# = f'/geographicSiteManagement/v1/continent/{obj.id}'

#     db.session.commit()

#     return 
# jsonify(obj),
# 200



# ###########################Country##############################





# @app.get('/geographicSiteManagement/v1/country')

# def 
# fetchAllCountry():

#     sites = Country.query.all()

#     result = countrySchemas.dump(sites)

#     return 
# jsonify(result),
# 200





# @app.get('/geographicSiteManagement/v1/country/<id>')

# def 
# fetchOneCountry(id):

#     obj = Country.query.get(int(id))

#     if obj:

#         result = countrySchema.dump(obj)

#         return 
# jsonify(result),
# 200

#     return 
# 'Not Found',
# 404





# @app.put('/geographicSiteManagement/v1/country/<id>')

# def 
# updateCountry(id):

#     obj = Country.query.get(int(id))

#     if 
# (obj):

#         if request.json:

#             obj.name 
# = request.json['name']

#             obj.code 
# = request.json['code']

#             obj.Continent 
# = request.json['Continent']



#             db.session.commit()

#             return 
# jsonify(obj),
# 200

#     return 
# 'not found',
# 404





# @app.delete('/geographicSiteManagement/v1/country/<id>')

# def 
# deleteCountry(id):

#     obj = Country.query.get(int(id))

#     if 
# (obj):

#         db.session.delete(obj)

#         db.session.commit()

#         return 
# 'Successful Removal',
# 204

#     return 
# 'Not Found',
# 404





# @app.post('/geographicSiteManagement/v1/country')

# def 
# saveCountry():

#     obj: Country 
# = Country(**request.json)

#     db.session.add(obj)

#     db.session.commit()

#     obj.href 
# = f'/geographicSiteManagement/v1/country/{obj.id}'

#     db.session.commit()

#     return 
# jsonify(obj),
# 200



# ###########################State##############################





# @app.get('/geographicSiteManagement/v1/state')

# def 
# fetchAllState():

#     sites = State.query.all()

#     result = stateSchemas.dump(sites)

#     return 
# jsonify(result),
# 200





# @app.get('/geographicSiteManagement/v1/state/<id>')

# def 
# fetchOneState(id):

#     obj = State.query.get(int(id))

#     if obj:

#         result = stateSchema.dump(obj)

#         return 
# jsonify(result),
# 200

#     return 
# 'Not Found',
# 404





# @app.put('/geographicSiteManagement/v1/state/<id>')

# def 
# updateState(id):

#     obj = State.query.get(int(id))

#     if 
# (obj):

#         if request.json:

#             obj.name 
# = request.json['name']

#             obj.code 
# = request.json['code']

#             obj.country 
# = request.json['country']

#             obj.zone 
# = request.json['zone']



#             db.session.commit()

#             return 
# jsonify(obj),
# 200

#     return 
# 'not found',
# 404





# @app.delete('/geographicSiteManagement/v1/state/<id>')

# def 
# deleteState(id):

#     obj = State.query.get(int(id))

#     if 
# (obj):

#         db.session.delete(obj)

#         db.session.commit()

#         return 
# 'Successful Removal',
# 204

#     return 
# 'Not Found',
# 404





# @app.post('/geographicSiteManagement/v1/state')

# def 
# saveState():

#     obj: State 
# = State(**request.json)

#     db.session.add(obj)

#     db.session.commit()

#     obj.href 
# = f'/geographicSiteManagement/v1/state/{obj.id}'

#     db.session.commit()

#     return 
# jsonify(obj),
# 200



# ###########################City##############################





# @app.get('/geographicSiteManagement/v1/city')

# def 
# fetchAllCity():

#     sites = City.query.all()

#     result = citySchemas.dump(sites)

#     return 
# jsonify(result),
# 200





# @app.get('/geographicSiteManagement/v1/city/<id>')

# def 
# fetchOneCity(id):

#     obj = City.query.get(int(id))

#     if obj:

#         result = citySchema.dump(obj)

#         return 
# jsonify(result),
# 200

#     return 
# 'Not Found',
# 404





# @app.put('/geographicSiteManagement/v1/city/<id>')

# def 
# updateCity(id):

#     obj = City.query.get(int(id))

#     if 
# (obj):

#         if request.json:

#             obj.name 
# = request.json['name']

#             obj.is_Capital 
# = request.json['is_Capital']

#             obj.state 
# = request.json['state']



#             db.session.commit()

#             return 
# jsonify(obj),
# 200

#     return 
# 'not found',
# 404





# @app.delete('/geographicSiteManagement/v1/city/<id>')

# def 
# deleteCity(id):

#     obj = City.query.get(int(id))

#     if 
# (obj):

#         db.session.delete(obj)

#         db.session.commit()

#         return 
# 'Successful Removal',
# 204

#     return 
# 'Not Found',
# 404





# @app.post('/geographicSiteManagement/v1/city')

# def 
# saveCity():

#     obj: City 
# = City(**request.json)

#     db.session.add(obj)

#     db.session.commit()

#     obj.href 
# = f'/geographicSiteManagement/v1/city/{obj.id}'

#     db.session.commit()

#     return 
# jsonify(obj),
# 200

# ###########################Locality##############################





# @app.get('/geographicSiteManagement/v1/locality')

# def 
# fetchAllLocality():

#     sites = Locality.query.all()

#     result = localitySchemas.dump(sites)

#     return 
# jsonify(result),
# 200





# @app.get('/geographicSiteManagement/v1/locality/<id>')

# def 
# fetchOneLocality(id):

#     obj = Locality.query.get(int(id))

#     if obj:

#         result = localitySchema.dump(obj)

#         return 
# jsonify(result),
# 200

#     return 
# 'Not Found',
# 404





# @app.put('/geographicSiteManagement/v1/locality/<id>')

# def 
# updateLocality(id):

#     obj = Locality.query.get(int(id))

#     if 
# (obj):

#         if request.json:

#             obj.name 
# = request.json['name']

#             obj.state 
# = request.json['state']

#             obj.city 
# = request.json['city']



#             db.session.commit()

#             return 
# jsonify(obj),
# 200

#     return 
# 'not found',
# 404





# @app.delete('/geographicSiteManagement/v1/locality/<id>')

# def 
# deleteLocality(id):

#     obj = Locality.query.get(int(id))

#     if 
# (obj):

#         db.session.delete(obj)

#         db.session.commit()

#         return 
# 'Successful Removal',
# 204

#     return 
# 'Not Found',
# 404





# @app.post('/geographicSiteManagement/v1/locality')

# def 
# saveLocality():

#     obj: Locality 
# = Locality(**request.json)

#     db.session.add(obj)

#     db.session.commit()

#     obj.href 
# = f'/geographicSiteManagement/v1/locality/{obj.id}'

#     db.session.commit()

#     return 
# jsonify(obj),
# 200



# ###########################Block_Hay##############################





# @app.get('/geographicSiteManagement/v1/block_hay')

# def 
# fetchAllBlock_Hay():

#     sites = Block_Hay.query.all()

#     result = block_haySchemas.dump(sites)

#     return 
# jsonify(result),
# 200





# @app.get('/geographicSiteManagement/v1/block_hay/<id>')

# def 
# fetchOneBlock_Hay(id):

#     obj = Block_Hay.query.get(int(id))

#     if obj:

#         result = block_haySchema.dump(obj)

#         return 
# jsonify(result),
# 200

#     return 
# 'Not Found',
# 404





# @app.put('/geographicSiteManagement/v1/block_hay/<id>')

# def 
# updateBlock_Hay(id):

#     obj = Block_Hay.query.get(int(id))

#     if 
# (obj):

#         if request.json:

#             obj.name 
# = request.json['name']

#             obj.state 
# = request.json['state']

#             obj.locality 
# = request.json['locality']



#             db.session.commit()

#             return 
# jsonify(obj),
# 200

#     return 
# 'not found',
# 404





# @app.delete('/geographicSiteManagement/v1/block_hay/<id>')

# def 
# deleteBlock_Hay(id):

#     obj = Block_Hay.query.get(int(id))

#     if 
# (obj):

#         db.session.delete(obj)

#         db.session.commit()

#         return 
# 'Successful Removal',
# 204

#     return 
# 'Not Found',
# 404





# @app.post('/geographicSiteManagement/v1/block_hay')

# def 
# saveBlock_Hay():

#     obj: Block_Hay 
# = Block_Hay(**request.json)

#     db.session.add(obj)

#     db.session.commit()

#     obj.href 
# = f'/geographicSiteManagement/v1/block_hay/{obj.id}'

#     db.session.commit()

#     return 
# jsonify(obj),
# 200



# ###########################Zone##############################





# @app.get('/geographicSiteManagement/v1/zone')

# def 
# fetchAllZone():

#     sites = Zone.query.all()

#     result = zoneSchemas.dump(sites)

#     return 
# jsonify(result),
# 200





# @app.get('/geographicSiteManagement/v1/zone/<id>')

# def 
# fetchOneZone(id):

#     obj = Zone.query.get(int(id))

#     if obj:

#         result = zoneSchema.dump(obj)

#         return 
# jsonify(result),
# 200

#     return 
# 'Not Found',
# 404





# @app.put('/geographicSiteManagement/v1/zone/<id>')

# def 
# updateZone(id):

#     obj = Zone.query.get(int(id))

#     if 
# (obj):

#         if request.json:

#             obj.name 
# = request.json['name']

#             obj.code 
# = request.json['code']



#             db.session.commit()

#             return 
# jsonify(obj),
# 200

#     return 
# 'not found',
# 404





# @app.delete('/geographicSiteManagement/v1/zone/<id>')

# def 
# deleteZone(id):

#     obj = Zone.query.get(int(id))

#     if 
# (obj):

#         db.session.delete(obj)

#         db.session.commit()

#         return 
# 'Successful Removal',
# 204

#     return 
# 'Not Found',
# 404





# @app.post('/geographicSiteManagement/v1/zone')

# def 
# saveZone():

#     obj: Zone 
# = Zone(**request.json)

#     db.session.add(obj)

#     db.session.commit()

#     obj.href 
# = f'/geographicSiteManagement/v1/zone/{obj.id}'

#     db.session.commit()

#     return 
# jsonify(obj),
# 200



# ###########################Office##############################





# @app.get('/geographicSiteManagement/v1/office')

# def 
# fetchAllOffice():

#     sites = Office.query.all()

#     result = officeSchemas.dump(sites)

#     return 
# jsonify(result),
# 200





# @app.get('/geographicSiteManagement/v1/office/<id>')

# def 
# fetchOneOffice(id):

#     obj = Office.query.get(int(id))

#     if obj:

#         result = officeSchema.dump(obj)

#         return 
# jsonify(result),
# 200

#     return 
# 'Not Found',
# 404





# @app.put('/geographicSiteManagement/v1/office/<id>')

# def 
# updateOffice(id):

#     obj = Office.query.get(int(id))

#     if 
# (obj):

#         if request.json:

#             obj.name 
# = request.json['name']

#             obj.address 
# = request.json['address']

#             obj.zone_id 
# = request.json['zone_id']



#             db.session.commit()

#             return 
# jsonify(obj),
# 200

#     return 
# 'not found',
# 404





# @app.delete('/geographicSiteManagement/v1/office/<id>')

# def 
# deleteOffice(id):

#     obj = Office.query.get(int(id))

#     if 
# (obj):

#         db.session.delete(obj)

#         db.session.commit()

#         return 
# 'Successful Removal',
# 204

#     return 
# 'Not Found',
# 404





# @app.post('/geographicSiteManagement/v1/office')

# def 
# saveOffice():

#     obj: Office 
# = Office(**request.json)

#     db.session.add(obj)

#     db.session.commit()

#     obj.href 
# = f'/geographicSiteManagement/v1/office/{obj.id}'

#     db.session.commit()

#     return 
# jsonify(obj),
# 200



# ###########################Cluster##############################





# # @app.get('/geographicSiteManagement/v1/cluster')

# # def fetchAllCluster():

# #     sites = Cluster.query.all()

# #     result = clusterSchemas.dump(sites)

# #     return jsonify(result), 200





# # @app.get('/geographicSiteManagement/v1/cluster/<id>')

# # def fetchOneCluster(id):

# #     obj = Cluster.query.get(int(id))

# #     if obj:

# #         result = clusterSchema.dump(obj)

# #         return jsonify(result), 200

# #     return 'Not Found', 404





# # @app.put('/geographicSiteManagement/v1/cluster/<id>')

# # def updateCluster(id):

# #     obj = Cluster.query.get(int(id))

# #     if (obj):

# #         if request.json:

# #             obj.name = request.json['name']

# #             obj.office_id = request.json['office_id']



# #             db.session.commit()

# #             return jsonify(obj), 200

# #     return 'not found', 404





# # @app.delete('/geographicSiteManagement/v1/cluster/<id>')

# # def deleteCluster(id):

# #     obj = Cluster.query.get(int(id))

# #     if (obj):

# #         db.session.delete(obj)

# #         db.session.commit()

# #         return 'Successful Removal', 204

# #     return 'Not Found', 404





# # @app.post('/geographicSiteManagement/v1/cluster')

# # def saveCluster():

# #     obj: Cluster = Cluster(**request.json)

# #     db.session.add(obj)

# #     db.session.commit()

# #     obj.href = f'/geographicSiteManagement/v1/cluster/{obj.id}'

# #     db.session.commit()

# #     return jsonify(obj), 200



#     manager.create_api(Cluster,
# methods=['GET',
# 'POST',
# 'DELETE'])





#     if __name__ 
# == "__main__":

#         app.run(debug=True,
# port=5000,
# host='0.0.0.0')



 
  
# Ismail Mosa
# IPCC Operations Specialist
# Zain SD
# Almashtal-Alriyad
# Mobile : 249901236447
# Office : +24990001-9312
# Email : Ismail.Mosa@ms.sd.zain.com
 
 
 
