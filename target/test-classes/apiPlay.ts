import { test, expect, request } from '@playwright/test';

test('GET /users returns 200', async () => {
  const api = await request.newContext();
  const response = await api.get('https://reqres.in/api/users/2');

  expect(response.status()).toBe(200);

  const body = await response.json();
  expect(body.data.id).toBe(2);
});
