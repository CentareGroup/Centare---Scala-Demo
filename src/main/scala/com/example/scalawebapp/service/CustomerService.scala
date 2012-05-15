/*
 * This code is in the public domain and may be used in any way you see fit, with the following conditions:
 *
 *     THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *     IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *     FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *     AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *     LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *     OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *     THE SOFTWARE.
 */

package com.example.scalawebapp.service

import scala.reflect.BeanProperty

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import com.example.scalawebapp.dao.CustomerDAO
import com.example.scalawebapp.data.Customer

trait CustomerService {
  def getAll: java.util.List[Customer]
  def save(customer: Customer): Long
  def update(customer: Customer)
  def get(customerId: Long): Customer
  def delete(customerId: Long)
}

@Service
class CustomerServiceImpl extends CustomerService {

  @Autowired
  @BeanProperty
  var customerDAO: CustomerDAO = _

  @Transactional
  def save(customer: Customer)  = customerDAO.save(customer)

  @Transactional
  def update(customer: Customer) = customerDAO.update(customer)

  @Transactional
  def delete(customerId: Long) = customerDAO.delete(customerId)

  @Transactional(readOnly = true)
  def get(customerId: Long) = customerDAO.get(customerId)

  @Transactional(readOnly = true)
  def getAll = customerDAO.getAll
  

}