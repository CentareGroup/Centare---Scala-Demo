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

package com.example.scalawebapp.data

import reflect.BeanProperty
import javax.persistence.Entity
import java.util.Date

@Entity
class Customer extends AbstractEntity {
  @BeanProperty var firstName: String = _
  @BeanProperty var lastName: String = _
  @BeanProperty var middleInitial: String = _
  @BeanProperty var dateOfBirth: Date = _
  
  override def toString = "Customer: id = %d %s, %s %s".format(id, lastName, firstName, middleInitial)
}
