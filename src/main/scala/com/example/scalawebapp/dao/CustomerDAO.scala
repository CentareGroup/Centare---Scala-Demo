package com.example.scalawebapp.dao

import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository

import com.example.scalawebapp.data.Customer

import javax.persistence.Entity

@Component("customerDAO")
class CustomerDAO extends BasicDAO[Customer, Long](classOf[Customer], classOf[Long])
