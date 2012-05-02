<%--
 * This code is in the public domain and may be used in any way you see fit, with the following conditions:
 *
 *     THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *     IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *     FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *     AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *     LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *     OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *     THE SOFTWARE.
 --%>
<%@page contentType="text/html;charset=utf-8"%>
<%@include file="../taglibs.jspf"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<tags:head title="New Customer" />
<body id="customer-new">
  <h1>New Customer</h1>
  <form:form commandName="customerData">
    <form:label path="firstName">First Name</form:label>
    <form:input path="firstName" />
    <form:errors path="firstName" cssClass="errors"/>
    <br/>
    <form:label path="lastName">Last Name</form:label>
    <form:input path="lastName" />
    <form:errors path="lastName" cssClass="errors"/>
    <br/>
    <form:label path="middleInitial">Middle Initial</form:label>
    <form:input path="middleInitial" />
    <form:errors path="middleInitial" cssClass="errors"/>
    <br/>
    <form:label path="dateOfBirth">Middle Initial</form:label>
    <form:input path="dateOfBirth" />
    <form:errors path="dateOfBirth" cssClass="errors"/>
    <br/>
    
    <input type="submit" value="Create Customer" />
  </form:form>
  <p>
    <a href="<c:url value="/"/>">Home</a>
  </p>
</body>
</html>
