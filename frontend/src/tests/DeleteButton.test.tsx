import { describe, test, expect, vi } from 'vitest';
import { render, screen, waitFor } from '@testing-library/react';
import userEvent from '@testing-library/user-event';
import DeleteButton from '../components/DeleteButton';

const doRender = async (ui: React.ReactElement) => waitFor(() => render(ui));

describe('DeleteButton', () => {
    test('calls fetch to delete and triggers callback', async () => {
        const itemId = 1;
        const onDelete = vi.fn();

        globalThis.fetch = vi.fn(() =>
            Promise.resolve({
                ok: true,
                json: () => Promise.resolve({}),
            } as Response)
        ) as typeof fetch;

        await doRender(<DeleteButton id={itemId} onDelete={onDelete} />);

        await userEvent.click(screen.getByRole('button', { name: /delete/i }));

        await waitFor(() => {
            expect(globalThis.fetch).toHaveBeenCalledWith(
                `http://localhost:8080/budget-items/${itemId}`,
                { method: 'DELETE' }
            );
            expect(onDelete).toHaveBeenCalled();
        });
    });
});
