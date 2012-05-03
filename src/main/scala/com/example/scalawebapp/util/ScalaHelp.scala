package com.example.scalawebapp.util

import scala.reflect.BeanProperty

object ScalaHelp {
  def capitalizeAll(message: String) = message.toLowerCase.split(" ").map(_.capitalize) mkString " " 
}
