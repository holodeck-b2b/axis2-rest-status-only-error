# Axis2 REST - Empty error responses
This project contains a simple Axis2 module that on an AxisFault will return an empty response with only HTTP error status code.
Additionally it will set a custom _"X-Error"_ HTTP header that includes the error message as contained in the AxisFault that
triggered the error response.

___
Code hosted at https://github.com/holodeck-b2b/axis2-rest-status-only-error.git  
Issue tracker https://github.com/holodeck-b2b/axis2-rest-status-only-error/issues  

## Usage
### Prerequisites
This module can be installed in any Axis2 version 1.6 or later running on a JRE 1.6 or later.

### Using
Just include a reference to this module to enable it for a service:
```xml
<module ref="rest-no-error-content"/>
```

## Contributing
We are using the simplified Github workflow to accept modifications which means you should:
* create an issue related to the problem you want to fix or the function you want to add (good for traceability and cross-reference)
* fork the repository
* create a branch (optionally with the reference to the issue in the name)
* write your code
* commit incrementally with readable and detailed commit messages
* run integration tests to check everything works on runtime
* submit a pull-request against the master branch of this repository

If your contribution is more than a patch, please contact us beforehand to discuss which branch you can best submit the pull request to.

### Submitting bugs
You can report issues directly on the [project Issue Tracker](https://github.com/holodeck-b2b/axis2-rest-status-only-error/issues).
Please document the steps to reproduce your problem in as much detail as you can (if needed and possible include screenshots).

## Versioning
Version numbering follows the [Semantic versioning](http://semver.org/) approach.

## License
This module is licensed under the Lesser General Public License V3 (LGPLv3) which is included in the LICENSE in the root of the project.

## Support
Commercial support is provided by Chasquis Consulting. Visit [Chasquis-Consulting.com](http://chasquis-consulting.com) for more information.
