package com.example.scalawebapp.dao

import java.io.Serializable
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import scala.reflect.BeanProperty

class BasicDAO[ENTITYTYPE, IDENTIFIERTYPE <% Serializable](var entityType: Class[ENTITYTYPE], var identifierType: Class[IDENTIFIERTYPE] ) {
  
  @Autowired
  @BeanProperty
  var sessionFactory: SessionFactory = _

  def save(entity: ENTITYTYPE): IDENTIFIERTYPE = session.save(entity).asInstanceOf[IDENTIFIERTYPE]
  def update(entity: ENTITYTYPE) = session.saveOrUpdate(entity)
  def delete(identifier: IDENTIFIERTYPE) = session.delete(get(identifier))
  def get(identifier: IDENTIFIERTYPE) = session.get(entityType, identifier).asInstanceOf[ENTITYTYPE]
  def getAll: java.util.List[ENTITYTYPE] = session.createCriteria(entityType).list().asInstanceOf[java.util.List[ENTITYTYPE]]
  
  def session = sessionFactory.getCurrentSession
}