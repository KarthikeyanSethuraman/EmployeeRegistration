import { Component, OnInit } from '@angular/core';
import { EmployeeService } from 'src/app/shared/employee.service';
import { NgForm } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import {BsDatepickerConfig} from 'ngx-bootstrap'
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  public dpConfig: Partial<BsDatepickerConfig> = new BsDatepickerConfig();
  constructor(private service: EmployeeService,
    private toastr: ToastrService,private datePipe:DatePipe) { 
      this.dpConfig.containerClass = 'theme-default';
      this.dpConfig.dateInputFormat = 'MM/DD/YYYY'
    }

  ngOnInit() {
    this.resetForm();
  }

  resetForm(form?: NgForm) {
    if (form != null)
      form.resetForm();
    this.service.formData = {
      id: null,
      firstName: '',
      lastName: '',
      department: '',
      dob: new Date(),
      gender: ''
    }
  }

  onChangeDepartment (event: any) {
    this.service.formData.department = event.target.value;
  }

  onChangeGender (event: any) {
    this.service.formData.gender = event.target.value;
  }

  onSubmit(form: NgForm) {
   form.value.dob = this.datePipe.transform(form.value.dob,"MM/dd/yyyy")
    if (form.value.id == null)
      this.insertRecord(form);
    else
      this.updateRecord(form);
  }

  insertRecord(form: NgForm) {
    this.service.postEmployee(form.value).subscribe(res => {
      this.toastr.success('Inserted successfully', 'EMP. Register');
      this.resetForm(form);
      this.service.refreshList();
    });
  }

  updateRecord(form: NgForm) {
    this.service.putEmployee(form.value).subscribe(res => {
      this.toastr.info('Updated successfully', 'EMP. Register');
      this.resetForm(form);
      this.service.refreshList();
    });

  }

}
