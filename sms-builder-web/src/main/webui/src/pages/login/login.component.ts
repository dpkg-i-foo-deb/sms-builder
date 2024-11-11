import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import {
  TuiButton,
  TuiAppearance,
  TuiTextfield,
  TuiIcon,
} from '@taiga-ui/core';
import { TuiCardLarge } from '@taiga-ui/layout';
import { TuiCheckbox, TuiPassword } from '@taiga-ui/kit';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    TuiButton,
    TuiAppearance,
    TuiCardLarge,
    RouterLink,
    TuiTextfield,
    ReactiveFormsModule,
    TuiIcon,
    TuiPassword,
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  private formBuilder = inject(FormBuilder);

  loginForm = this.formBuilder.group({
    username: ['', Validators.required],
    password: ['', Validators.required],
  });
}
