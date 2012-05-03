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

package com.example.scalawebapp.controller

import org.springframework.stereotype.Controller
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{ PathVariable, ModelAttribute, RequestMapping, RequestParam }
import org.springframework.web.bind.annotation.RequestMethod._
import org.springframework.web.servlet.ModelAndView
import com.example.scalawebapp.data.Customer
import com.example.scalawebapp.service.CustomerService
import reflect.BeanProperty
import javax.validation.constraints.NotNull
import org.hibernate.validator.constraints.NotEmpty
import javax.validation.Valid
import org.springframework.validation.BindingResult
import collection.JavaConversions
import org.springframework.context.annotation.Scope
import java.util.Date

@Controller
class CustomerController {

  import ControllerTools._

  @Autowired
  val customerService: CustomerService = null

  @RequestMapping(value = Array("/customers/new"), method = Array(GET))
  def showNewCustomerForm() = new ModelAndView("customer/customer-new", "customerData", new CustomerPageData)

  @RequestMapping(value = Array("/customers/new"), method = Array(POST))
  def createNewCustomer(
    @Valid @ModelAttribute("customerData") customerData: CustomerPageData,
    bindingResult: BindingResult): String = {
    if (bindingResult.hasErrors) {
      "customer/customer-new"
    } else {
      val newCustomer = new Customer
      customerData.copyTo(newCustomer)
      "redirect:/customers/" + customerService.save(newCustomer) + ".html"
    }
  }

  @RequestMapping(value = Array("/customers/{customerId}"), method = Array(GET))
  def viewCustomer(
    @PathVariable customerId: Long,
    @RequestParam(required = false) edit: String) = {
    val customer: Customer = customerService.get(customerId)
    if (edit == null) {
      new ModelAndView("customer/customer-view", "customer", customer)
    } else {
      new ModelAndView("customer/customer-edit", Map("customer" -> customer, "customerData" -> CustomerPageData(customer)))
    }
  }

  @RequestMapping(value = Array("/customers/{customerId}"), method = Array(POST))
  def editCustomer(
    @PathVariable customerId: Long,
    @Valid @ModelAttribute("customerData") customerData: CustomerPageData,
    bindingResult: BindingResult): ModelAndView = {
    val customer = customerService.get(customerId)
    if (bindingResult.hasErrors) {
      new ModelAndView("customer/customer-edit", "customer", customer)
    } else {
      customerData.copyTo(customer)
      customerService.update(customer)
      new ModelAndView("redirect:/customers/{customerId}.html")
    }
  }

  @RequestMapping(value = Array("/customers/{customerId}"), method = Array(DELETE))
  def deleteCustomer(@PathVariable customerId: Long) = {
    customerService.delete(customerId)
    "redirect:/"
  }

  @RequestMapping(value = Array("/customers"), method = Array(DELETE))
  def deleteAllCustomers() = {
    for (c: Customer <- JavaConversions.asScalaBuffer(customerService.getAll)) {
      customerService.delete(c.id)
    }
    "redirect:/"
  }
}

class CustomerPageData {
  @NotNull
  @NotEmpty
  @BeanProperty var firstName: String = _;

  @NotNull
  @NotEmpty
  @BeanProperty var lastName: String = _;

  @BeanProperty var middleInitial: String = _;

  @BeanProperty var dateOfBirth: Date = _;

  override def toString = "[CustomerPageData: name = " + firstName + "]"

  def copyTo(customer: Customer): Unit = {
    customer.firstName = firstName
    customer.lastName = lastName
    customer.middleInitial = middleInitial
    customer.dateOfBirth = dateOfBirth
  }

  def copyFrom(customer: Customer): Unit = {
    firstName = customer.firstName
    lastName = customer.lastName
    middleInitial = customer.middleInitial
    dateOfBirth = customer.dateOfBirth
  }
}

object CustomerPageData {
  def apply(c: Customer): CustomerPageData = {
    val data = new CustomerPageData
    data.copyFrom(c)
    data
  }
}
