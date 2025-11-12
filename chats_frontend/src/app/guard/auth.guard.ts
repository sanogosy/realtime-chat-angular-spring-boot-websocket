import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const isLoggedIn = localStorage.getItem('loginSuccess') === 'true';

  if (isLoggedIn === null) {
    router.navigate(['/login']);
    return false;
  }
  return true;
};
