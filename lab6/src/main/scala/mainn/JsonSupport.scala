package mainn

import spray.json.RootJsonFormat
import spray.json.DefaultJsonProtocol._


trait JsonSupport {
  implicit val f1: RootJsonFormat[AlmatyBike.GetBikeInfoFromStation]= jsonFormat2(AlmatyBike.GetBikeInfoFromStation)

  implicit val f2: RootJsonFormat[AlmatyBike.CreatesBike]= jsonFormat3(AlmatyBike.CreatesBike)

  implicit val f3: RootJsonFormat[AlmatyBike.getStationsNames]= jsonFormat1(AlmatyBike.getStationsNames)

  implicit val f4: RootJsonFormat[AlmatyBike.CreateStation]= jsonFormat2(AlmatyBike.CreateStation)

  implicit val f5: RootJsonFormat[AlmatyBike.Txt]= jsonFormat1(AlmatyBike.Txt)

  implicit val f6: RootJsonFormat[BikeStation.GetBikeInfo]= jsonFormat1(BikeStation.GetBikeInfo)

  implicit val f7: RootJsonFormat[BikeStation.CreateBike]= jsonFormat2(BikeStation.CreateBike)

  implicit val f8: RootJsonFormat[BikeStation.MsgFromBikeStation]= jsonFormat1(BikeStation.MsgFromBikeStation)

  implicit val f9: RootJsonFormat[Bike.GetInfo]= jsonFormat1(Bike.GetInfo)

  implicit val f10: RootJsonFormat[AlmatyBike.deleteStation]=jsonFormat1(AlmatyBike.deleteStation)

  implicit val f11: RootJsonFormat[AlmatyBike.MessageDelStation]=jsonFormat1(AlmatyBike.MessageDelStation)
}
